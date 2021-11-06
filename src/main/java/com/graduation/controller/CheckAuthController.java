package com.graduation.controller;

import cn.hutool.crypto.digest.MD5;
import com.graduation.utils.AesUtils;
import com.graduation.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

/**
 * Description 文件下载验证
 *
 * @author shaoming
 * @date 2021/9/8 09:20
 * @since 1.0
 */
@RestController
@RequestMapping("/auth")
public class CheckAuthController {

    @Autowired
    private RedisUtils redisUtils;

    private static final String BIG_FILE_UPLOAD_TOKEN = "9ee60e59-cb0f-4578-aaba-29b9fc2919ca";
    private static final String TOKEN_SALT = "localhost@shaomingauth_token";
    private static final Integer MD5_LENGTH = 32;


    @RequestMapping("/check")
    public String checkAuth(@RequestParam("auth_token") String token,
                            @RequestParam(name = "path",required = false) String path) throws Exception {
        if (BIG_FILE_UPLOAD_TOKEN.equals(token)){
            return "ok";
        }
        if (redisUtils.hasKey("token-"+path)){
            path = URLDecoder.decode(path, "UTF-8");
            token = token.trim();
            if (token.length()>MD5_LENGTH){
                token = token.substring(0,MD5_LENGTH);
            }
            String code = AesUtils.getCheckCodeByDecryptStr(path);
            String authToken = MD5.create().digestHex(MD5.create().digestHex(TOKEN_SALT+code));
            if (token.equals(authToken)){
                return "ok";
            }
        }
        return "fail";
    }
}
