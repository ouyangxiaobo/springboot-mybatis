package com.github.mydemo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class Member {
    private Integer id;

    private String username;


    private String password;

    private Date birthday;

    private String tel;

    private Integer gender;

    private String remark;


}