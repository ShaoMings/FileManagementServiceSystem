package com.graduation.utils;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;

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

    public static void main(String[] args) {
//        System.out.println(encode("shaoming"));
    }

}

