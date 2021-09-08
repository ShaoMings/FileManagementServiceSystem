package com.graduation.utils;

import cn.hutool.crypto.digest.MD5;

/**
 * Description 管理token的工具类
 *
 * @author shaoming
 * @date 2021/9/8 10:04
 * @since 1.0
 */
public class TokenUtils {

    public static String getAuthToken(String checkCode){
        return MD5.create().digestHex(MD5.create().digestHex(Constant.MAKE_AUTH_TOKEN_SALT + checkCode));
    }
}
