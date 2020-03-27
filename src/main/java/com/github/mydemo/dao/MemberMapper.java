package com.github.mydemo.dao;

import com.github.mydemo.model.Member;
import com.github.mydemo.model.ext.MemberExt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member selectMemberByUserName(String username);

    List<Member> listMemberPage(MemberExt memberExt);
}