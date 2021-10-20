package com.graduation.controller.adapter.gitee;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.graduation.controller.BaseController;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.gitee.AuthTokenVo;
import com.graduation.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description gite 认证控制器
 *
 * @author shaoming
 * @date 2021/10/18 15:12
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class AuthenticationController extends BaseController {

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("/tokenCheck")
    public FileResponseVo checkToken(){
        if (redisUtils.hasKey(getUser().getUsername() + "-auth_token")) {
            String token = (String) redisUtils.get(getUser().getUsername() + "-auth_token");
            return FileResponseVo.success(token);
        }
        return FileResponseVo.fail("no token!");
    }

    @RequestMapping("/codeCheck")
    public FileResponseVo checkCode(){
        if (redisUtils.hasKey(getUser().getUsername() + "-auth_code")) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("no code!");
    }


    @RequestMapping("/certification")
    public FileResponseVo certification(String clientId, String clientSecret, String backUrl) {
        if (redisUtils.hasKey(getUser().getUsername() + "-auth_code")) {
            String code = (String) redisUtils.get(getUser().getUsername() + "-auth_code");
            String request = "https://gitee.com/oauth/token?grant_type=authorization_code&code="
                    + code + "&client_id=" + clientId + "&redirect_uri=" + backUrl + "&client_secret=" + clientSecret;
            Map<String, Object> params = new HashMap<>();
            params.put("grant_type", "authorization_code");
            params.put("code", code);
            params.put("client_id", clientId);
            params.put("redirect_uri", backUrl);
            params.put("client_secret", clientSecret);
            String json = HttpUtil.post(request, params);
            AuthTokenVo tokenVo = JSONUtil.toBean(json, AuthTokenVo.class);
            if (StringUtils.isNotBlank(tokenVo.getAccessToken())){
                redisUtils.set(getUser().getUsername() + "-auth_token",tokenVo.getAccessToken()
                        ,Long.parseLong(tokenVo.getExpiresIn()));
                redisUtils.set(getUser().getUsername() + "-auth_refresh_token",tokenVo.getRefreshToken()
                        ,Long.parseLong(tokenVo.getExpiresIn()));
                redisUtils.set(getUser().getUsername() + "-auth_refresh",false
                        ,Long.parseLong(tokenVo.getExpiresIn()));
            }
            return FileResponseVo.success(tokenVo);
        }
        return FileResponseVo.fail("error");
    }


    @RequestMapping("/authorization")
    public FileResponseVo authentication(String token,Boolean refresh,String method){
            if (StringUtils.isNotBlank(token)){
                if ("1".equals(method)){
                    if (redisUtils.hasKey(getUser().getUsername() + "-auth_token")){
                        if (redisUtils.get(getUser().getUsername() + "-auth_token").equals(token)){
                            redisUtils.set(getUser().getUsername() + "-auth_refresh",refresh);
                            return FileResponseVo.success("accredit successful!");
                        }else {
                            return FileResponseVo.fail("token is not correct!");
                        }
                    }else {
                        return FileResponseVo.fail("token is overdue!");
                    }
                }else {
                    redisUtils.set(getUser().getUsername() + "-auth_token",token);
                    redisUtils.set(getUser().getUsername() + "-auth_refresh",refresh);
                    return FileResponseVo.success("accredit successful!");
                }
            }
            return FileResponseVo.fail("error!");
    }

}
