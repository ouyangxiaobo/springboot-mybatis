package com.github.mydemo.controller;

import com.github.mydemo.common.result.Results;
import com.github.mydemo.model.Member;
import com.github.mydemo.model.ext.MemberExt;
import com.github.mydemo.service.MemberService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: ouyang
 * Date:2020/3/27 12:11
 **/
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {


    @Autowired
    private MemberService memberService;


    @PostMapping("/addMember")
    @ApiOperation(notes = "用户添加",value = "用户添加")
    public Results<Integer> addMember(Member member){
        try {
            log.info("【用户添加信息】,{}",member.toString());

            return memberService.addMember(member);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户添加错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @PostMapping("/editMember")
    @ApiOperation(notes = "用户编辑",value = "用户编辑")
    public Results<Integer> editMember(Member member){
        try {
            log.info("【用户编辑信息】,{}",member.toString());

            return memberService.editMember(member);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户编辑错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @PostMapping("/delOneMember")
    @ApiOperation(notes = "删除单个用户",value = "删除单个用户")
    public Results<Integer> delOneMember(Integer userId){
        try {
            log.info("【用户删除ID】,{}",userId);

            return memberService.delOneMember(userId);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户删除错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @PostMapping("/delManyMember")
    @ApiOperation(notes = "删除多个用户",value = "删除多个用户")
    public Results<Integer> delManyMember(Integer[] userIds){
        try {
            for (Integer userId : userIds){
                log.info("【多个用户删除ID】,{}",userId);
            }

            return memberService.delManyMember(userIds);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【多个用户删除错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @GetMapping("/getOneMemberById")
    @ApiOperation(notes = "查询单个用户",value = "查询单个用户")
    public Results<Member> getOneMemberById(Integer userId){
        try {
            log.info("【单个用户ID】,{}",userId);
            return memberService.getOneMemberById(userId);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【单个用户错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @GetMapping("/getMemberByName")
    @ApiOperation(notes = "查询单个用户--name",value = "查询单个用户--name")
    public Results<Member> getMemberByName(String username){
        try {
            log.info("【单个用户username】,{}",username);
            return memberService.getMemberByUserName(username);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【单个用户错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @PostMapping("/pageMemberInfo")
    @ApiOperation(notes = "分页查询",value = "分页查询")
    public PageInfo<Member> pageMemberInfo(Integer pageNum, Integer pageSize, MemberExt memberExt){
        try {
            log.info("【分页查询用户pageNum】,{}",pageNum);
            log.info("【分页查询用户pageSize】,{}",pageSize);
            log.info("【分页查询用户memberExt】,{}",memberExt.toString());
            return memberService.pageMemberInfo(pageNum,pageSize,memberExt);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【分页查询用户错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @PostMapping("/findPasswordNew")
    @ApiOperation(notes = "找回密码",value = "找回密码")
    public Results<Integer> findPasswordNew(String username,String tel,String newPassword){
        try {
            log.info("【找回密码-username】,{}",username);
            log.info("【找回密码-tel】,{}",tel);
            log.info("【找回密码-newPassword】,{}",newPassword);
            return memberService.findPassword(username,tel,newPassword);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【找回密码用户错误信息】,{}",e.getMessage() );
        }

        return null;
    }

    @PostMapping("/loginMember")
    @ApiOperation(notes = "用户登录",value = "用户登录")
    public Results  loginMember(String username,String password){
        try {
            log.info("【用户登录-username】,{}",username);
            log.info("【用户登录-tpassword】,{}",password);
            return memberService.loginMember(username,password);

        }catch (Exception e){
            e.printStackTrace();
            log.error("【用户登录错误信息】,{}",e.getMessage() );
        }

        return null;
    }








}
