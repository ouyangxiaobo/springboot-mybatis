package com.github.mydemo.service;

import com.github.mydemo.common.result.Results;
import com.github.mydemo.model.Member;
import com.github.mydemo.model.ext.MemberExt;
import com.github.pagehelper.PageInfo;

public interface MemberService {


    /*添加用户*/
    Results<Integer> addMember(Member member) throws Exception;

    /*编辑用户*/
    Results<Integer> editMember(Member member) throws Exception;

    /*删除单个用户*/
    Results<Integer> delOneMember(Integer userId) throws Exception;

    /*删除多个用户*/
    Results<Integer> delManyMember(Integer[] userIds) throws Exception;

    /*查询单个用户--根据Id*/
    Results<Member> getOneMemberById(Integer userId);

    /*查询单个用户--根据用户名*/
    Results<Member> getMemberByUserName(String username);

    /*分页模糊查询用户*/
    PageInfo<Member> pageMemberInfo(Integer pageNum, Integer pageSize, MemberExt memberExt);

    /*找回密码--根据用户名和手机号码*/
    Results<Integer> findPassword(String username,String tel,String newPassword);

    /*用户登陆*/
    Results loginMember(String username,String password);
}
