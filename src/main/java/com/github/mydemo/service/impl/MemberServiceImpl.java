package com.github.mydemo.service.impl;

import com.github.mydemo.common.result.ResponseCode;
import com.github.mydemo.common.result.Results;
import com.github.mydemo.common.utils.MD5;
import com.github.mydemo.common.utils.TelUtil;
import com.github.mydemo.dao.MemberMapper;
import com.github.mydemo.model.Member;
import com.github.mydemo.model.ext.MemberExt;
import com.github.mydemo.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * author: ouyang
 * Date:2020/3/27 11:45
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;


   /**
    * @ClassName MemberServiceImpl
    * @Description : 添加用户
    * @Return :
    * @Author : ouyang
    * @Date : 2020/3/27 11:46
   **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> addMember(Member member) throws Exception {
        //1.判断为空
        if(StringUtils.isBlank(member.getUsername() )||
                StringUtils.isBlank(member.getPassword() )||
                        StringUtils.isBlank(member.getTel())
                ){
            return Results.failure(ResponseCode.PARAMETER_MISSING.getCode(),ResponseCode.PARAMETER_MISSING.getMessage());
        }
        //2.判断是否重名
        Member memberItem=memberMapper.selectMemberByUserName(member.getUsername());
        if(memberItem!=null){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }

        //3.判断手机号码格式是否正确
        boolean cellPhoneNo = TelUtil.isCellPhoneNo(member.getTel());
        if(!cellPhoneNo){
            return Results.failure(ResponseCode.PHONE_IS_ERROR.getCode(),ResponseCode.PHONE_IS_ERROR.getMessage());
        }
        //4.密码加密
        member.setPassword(MD5.crypt(member.getPassword()));

        //5.设置默认出生日期
        if(member.getBirthday()==null){
            member.setBirthday(new Date());
        }

        int result=memberMapper.insertSelective(member);
        if(result>0){

            return  Results.success(result);
        }else {
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage() );
        }

    }

    /**
     * @ClassName MemberServiceImpl
     * @Description : 编辑用户
     * @Return :
     * @Author : ouyang
     * @Date : 2020/3/27 12:19
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> editMember(Member member) throws Exception {
        //1.判断为空
        if(StringUtils.isBlank(member.getUsername() )||
                StringUtils.isBlank(member.getPassword() )||
                StringUtils.isBlank(member.getTel())
        ){
            return Results.failure(ResponseCode.PARAMETER_MISSING.getCode(),ResponseCode.PARAMETER_MISSING.getMessage());
        }
        //2.判断是否重名
        Member memberItem=memberMapper.selectMemberByUserName(member.getUsername());
        if(memberItem!=null){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }

        //3.判断手机号码格式是否正确
        boolean cellPhoneNo = TelUtil.isCellPhoneNo(member.getTel());
        if(!cellPhoneNo){
            return Results.failure(ResponseCode.PHONE_IS_ERROR.getCode(),ResponseCode.PHONE_IS_ERROR.getMessage());
        }
        //4.密码加密

        member.setPassword(MD5.crypt(member.getPassword()));

        //5.设置默认出生日期
        if(member.getBirthday()==null){
            member.setBirthday(new Date());
        }

        int result=memberMapper.updateByPrimaryKeySelective(member);
        if(result>0){

            return  Results.success(result);
        }else {
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage() );
        }

    }

    /**
     * @ClassName MemberServiceImpl
     * @Description : 删除单个用户
     * @Return :
     * @Author : ouyang
     * @Date : 2020/3/27 12:28
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> delOneMember(Integer userId) throws Exception {
        //1.判断用户Id不为空或者小于0
        if(userId==null || userId<=0){
            return Results.failure(ResponseCode.USER_ID_IS_NULL.getCode(),ResponseCode.USER_ID_IS_NULL.getMessage());
        }
        //2.判断用户是否存在
        Member member = memberMapper.selectByPrimaryKey(userId);
        if(member==null){
            return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
        }
        int result=memberMapper.deleteByPrimaryKey(userId);
        if(result>0){
            return Results.success(result);
        }else {
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
        }

    }


   /**
    * @ClassName MemberServiceImpl
    * @Description : 删除多个用户
    * @Return :
    * @Author : ouyang
    * @Date : 2020/3/27 12:34
   **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> delManyMember(Integer[] userIds) throws Exception {

        for(Integer userId: userIds){
            //1.判断用户Id不为空或者小于0
            if(userId==null || userId<=0){
                return Results.failure(ResponseCode.USER_ID_IS_NULL.getCode(),ResponseCode.USER_ID_IS_NULL.getMessage());
            }
            //2.判断用户是否存在
            Member member = memberMapper.selectByPrimaryKey(userId);
            if(member==null){
                return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
            }

           int  result=memberMapper.deleteByPrimaryKey(userId);
            if(result>0){
                return Results.success(result);
            }

        }
      return Results.failure();
    }

    /**
     * @ClassName MemberServiceImpl
     * @Description : 查询单个用户--根据Id
     * @Return :
     * @Author : ouyang
     * @Date : 2020/3/27 12:38
    **/
    @Override
    public Results<Member> getOneMemberById(Integer userId) {
        //1.判断用户Id不为空或者小于0
        if(userId==null || userId<=0){
            return Results.failure(ResponseCode.USER_ID_IS_NULL.getCode(),ResponseCode.USER_ID_IS_NULL.getMessage());
        }
        //2.判断用户是否存在
        Member member = memberMapper.selectByPrimaryKey(userId);
        if(member==null){
            return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
        }
        Member memberItem=memberMapper.selectByPrimaryKey(userId);
        return  Results.success(memberItem);
    }

    @Override
    public Results<Member> getMemberByUserName(String username) {
        //1.判断用户名是否合法
        if(StringUtils.isBlank(username.trim())){
            return Results.failure(ResponseCode.PARAMETER_MISSING.getCode(),ResponseCode.PARAMETER_MISSING.getMessage());
        }
        //2.查询是否存在
        Member member=memberMapper.selectMemberByUserName(username);
        if(member==null){
            return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
        }

        return Results.success(member);
    }

    /**
     * @ClassName MemberServiceImpl
     * @Description : 用户分页多条件查询
     * @Return :
     * @Author : ouyang
     * @Date : 2020/3/27 12:46
    **/
    @Override
    public PageInfo<Member> pageMemberInfo(Integer pageNum, Integer pageSize, MemberExt memberExt) {
        if(pageNum==null || pageNum==0){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=2;
        }

        List<Member> memberList=memberMapper.listMemberPage(memberExt);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Member> pageInfo=new PageInfo<>(memberList);

        return pageInfo;
    }


    /**
     * @ClassName MemberServiceImpl
     * @Description : 找回密码--根据用户名和手机号码
     * @Return :
     * @Author : ouyang
     * @Date : 2020/3/27 13:12
    **/
    @Override
    public Results<Integer> findPassword(String username, String tel,String newPassword) {
        //1.判断是否为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(tel)){
            return Results.failure(ResponseCode.PARAMETER_MISSING.getCode(),ResponseCode.PARAMETER_MISSING.getMessage());
        }
        //2.判断用户是否存在
        Member member = memberMapper.selectMemberByUserName(username);
        if(member==null){
            return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
        }
        //3.判断手机格式和该用户是否绑定这个手机号码
        boolean cellPhoneNo = TelUtil.isCellPhoneNo(tel);
        if(!cellPhoneNo){
            return Results.failure(ResponseCode.PHONE_IS_ERROR.getCode(),ResponseCode.PHONE_IS_ERROR.getMessage());
        }
        if(!member.getTel().equals(tel)){
            return Results.failure(ResponseCode.USER_PHONE_IS_NOT_EQUALS.getCode(), ResponseCode.USER_PHONE_IS_NOT_EQUALS.getMessage());
        }

        //返回密码 --由于Md5没有反转密码的算法，所以不支持返回明文密码，换种思路，通过修改密码，让用户输入新密码即可,更新用户即可

        member.setPassword(MD5.crypt(newPassword));

       int result= memberMapper.updateByPrimaryKeySelective(member);

        return Results.success(result);

    }

    /**
     * @ClassName MemberServiceImpl
     * @Description : 用户登陆
     * @Return :
     * @Author : ouyang
     * @Date : 2020/3/27 13:40
    **/
    @Override
    public Results loginMember(String username, String password) {

        //1.判断是否为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Results.failure(ResponseCode.PARAMETER_MISSING.getCode(),ResponseCode.PARAMETER_MISSING.getMessage());
        }
        //2.查询是否存在
        Member member=memberMapper.selectMemberByUserName(username);
        if(member==null){
            return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
        }



        if(member.getUsername().equals(username) && member.getPassword().equals(MD5.crypt(password))){
           return Results.success(ResponseCode.USER_LOGIN_SUCCESS.getMessage());
        }else {
            return Results.failure(ResponseCode.USER_LOGIN_FAIL.getCode(),ResponseCode.USER_LOGIN_FAIL.getMessage());
        }


    }
}
