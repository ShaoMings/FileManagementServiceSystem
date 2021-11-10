package com.graduation.utils;

import cn.hutool.crypto.digest.MD5;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Description 管理token的工具类
 *
 * @author shaoming
 * @date 2021/9/8 10:04
 * @since 1.0
 */
public class TokenUtils {

    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 用于常用的文件访问认证token生成算法 默认时效是30分钟
     * @param checkCode 校验码(盐值)
     * @return token
     */
    public static String getAuthToken(String checkCode){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(checkCode);
        return JWT.create().withExpiresAt(date).sign(algorithm);
    }

    /**
     * 校验token是否正确
     * @param token token
     * @param checkCode 校验code
     * @return true正确
     */
    public static boolean verifyToken(String token,String checkCode){
        try {
            Algorithm algorithm = Algorithm.HMAC256(checkCode);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 检查token是否过期失效
     * @param token token
     * @return true失效 false有效
     */
    public static boolean isExpire(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().getTime() < System.currentTimeMillis();
    }

    /**
     * 创建分享链接专用的token生成算法
     * @param checkCode 校验码
     * @return token
     */
    public static String getShareAuthToken(String checkCode){
        return MD5.create().digestHex(MD5.create().digestHex(Constant.MAKE_AUTH_TOKEN_SALT + checkCode));
    }
}
