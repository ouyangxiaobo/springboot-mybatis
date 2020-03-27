package com.github.mydemo.common.utils;

import java.util.regex.Pattern;

/**
 * author: ouyang
 * Date:2020/2/8 14:54
 **/
public class EmailUtil {

    /**
     * 邮箱
     */
    private static final String EMAIL_REGEX="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";



    /**
     * 邮箱格式校验
     * @param email
     * @return
     */
    public static boolean  checkEmail(String email){
        return Pattern.matches(EMAIL_REGEX, email.trim());
    }


}
