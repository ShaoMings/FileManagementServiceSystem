package com.graduation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description 正则表达式校验某些字符串是否合法
 *
 * @author shaoming
 * @date 2021/8/16 17:50
 * @since 1.0
 */
public class RegexUtils {

    /**
     * 验证传入字符串是否是URL
     * @param url url
     * @return boolean
     */
    public static boolean verifyUrl(String url) {
        String regEx = "[a-zA-z]+://[^\\s]*";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

}
