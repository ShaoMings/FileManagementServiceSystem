package com.graduation.controller;

import cn.hutool.crypto.digest.MD5;
import com.graduation.utils.AesUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.net.URLDecoder;

/**
 * Description 文件下载验证
 *
 * @author shaoming
 * @date 2021/9/8 09:20
 * @since 1.0
 */
@RestController
public class CheckAuthController {

    private static final String BIG_FILE_UPLOAD_TOKEN = "9ee60e59-cb0f-4578-aaba-29b9fc2919ca";
    private static final String TOKEN_SALT = "localhost@shaomingauth_token";

    @RequestMapping("/check")
    public String checkAuth(@RequestParam("auth_token") String token,
                            @RequestParam(name = "path",required = false) String path) throws Exception {
        if (BIG_FILE_UPLOAD_TOKEN.equals(token)){
            return "ok";
        }
        path = URLDecoder.decode(path, "UTF-8");
        String code = AesUtils.getCheckCodeByDecryptStr(path);
        String auth_token = MD5.create().digestHex(MD5.create().digestHex(TOKEN_SALT+code));
        if (token.equals(auth_token)){
            return "ok";
        }
        return "fail";
    }
}
