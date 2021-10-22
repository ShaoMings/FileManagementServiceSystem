package com.graduation.utils;

/**
 * Description 一般方法工具类
 *
 * @author shaoming
 * @date 2021/10/22 15:17
 * @since 1.0
 */
public class CommonUtils {
    private CommonUtils(){}

    /**
     * 首字母大写
     * @param string
     * @return
     */
    public static String toUpperFirstChar(String string) {
        char[] chars = string.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
            return String.valueOf(chars);
        }
        return string;
    }
}
