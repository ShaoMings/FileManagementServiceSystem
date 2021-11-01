package com.graduation.utils;

import cn.hutool.crypto.SecureUtil;
import java.io.FileOutputStream;
import java.security.MessageDigest;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/8/6 11:26
 * @since 1.0
 */
public class Md5Utils {

    private static final String SALT = "test";

    public static String encode(String password) {
        password += SALT;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        char[] chars = password.toCharArray();
        byte[] bytes = new byte[chars.length];

        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        byte[] md5Bytes = md5.digest(bytes);
            StringBuilder hexValue = new StringBuilder();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int)md5Bytes[i]) & 0xff;
                if (val<16){
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        return hexValue.toString();
    }

    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }



    public static void main(String[] args) {
        String s = "380104eeca293cd6e3e7d09dcb5aa18e";
        System.out.println(s);
        System.out.println(encode(s));
        System.out.println(convertMD5(s));
        System.out.println(convertMD5(convertMD5(s)));
    }

}

