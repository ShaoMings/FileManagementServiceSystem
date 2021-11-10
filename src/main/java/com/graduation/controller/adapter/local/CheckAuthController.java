package com.graduation.controller.adapter.local;

import cn.hutool.crypto.digest.MD5;
import com.graduation.utils.AesUtils;
import com.graduation.utils.Constant;
import com.graduation.utils.RedisUtils;
import com.graduation.utils.TokenUtils;
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

    @RequestMapping("/check")
    public String checkAuth(@RequestParam("auth_token") String token,
                            @RequestParam(name = "path", required = false) String path) throws Exception {
        if (BIG_FILE_UPLOAD_TOKEN.equals(token)) {
            return "ok";
        }
        path = URLDecoder.decode(path, "UTF-8");
        token = token.trim();
        if (token.contains(",")) {
            token = token.substring(token.indexOf(",")+1);
        }
        String code = AesUtils.getCheckCodeByDecryptStr(path);
        if (TokenUtils.verifyToken(token, code)) {
            if (!TokenUtils.isExpire(token)) {
                return "ok";
            }
        }
        if (redisUtils.hasKey("token-" + path)) {
            String authToken = MD5.create().digestHex(MD5.create().digestHex(Constant.MAKE_AUTH_TOKEN_SALT + code));
            if (token.equals(authToken)) {
                return "ok";
            }
        }
        return "fail";
    }
}
