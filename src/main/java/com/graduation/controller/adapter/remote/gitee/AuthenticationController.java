package com.graduation.controller.adapter.remote.gitee;

import com.graduation.controller.adapter.local.BaseController;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.gitee.AuthTokenVo;
import com.graduation.repo.adapter.GiteeAdapter;
import com.graduation.utils.RedisUtils;
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
    @Autowired
    private GiteeAdapter giteeAdapter;


    /**
     * 检查 token是否存在并是否有效
     *
     * @return ture有效
     */
    @RequestMapping("/tokenCheck")
    public FileResponseVo checkToken() {
        if (redisUtils.hasKey(getUser().getUsername() + "-auth_token")) {
            String token = (String) redisUtils.get(getUser().getUsername() + "-auth_token");
            if (giteeAdapter.checkToken(token)) {
                return FileResponseVo.success(token);
            }
        }
        return FileResponseVo.fail("no token!");
    }

    /**
     * 检查授权码是否存在
     *
     * @return ture存在
     */
    @RequestMapping("/codeCheck")
    public FileResponseVo checkCode() {
        if (redisUtils.hasKey(getUser().getUsername() + "-auth_code")) {
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("no code!");
    }


    /**
     * OAuth2 认证 通过授权码发起post请求获取token
     *
     * @param clientId     clientId
     * @param clientSecret clientSecret
     * @param backUrl      获取成功回调地址(跳转地址)不能与创建app时填写的不一致
     * @return 响应对象
     */
    @RequestMapping("/certification")
    public FileResponseVo certification(String clientId, String clientSecret, String backUrl) {
        AuthTokenVo tokenVo = giteeAdapter.oauth2Authenticator(getUser().getUsername(), clientId, clientSecret, backUrl);
        return tokenVo == null?FileResponseVo.fail("error"):FileResponseVo.success(tokenVo);
    }


    /**
     * 个人令牌或 OAuth2 授权  主要通过token有效性验证是否进行授权
     *
     * @param token   token
     * @param refresh 是否 refresh token
     * @param method  0 表示 个人令牌授权 1表示OAuth2授权
     * @return 响应对象
     */
    @RequestMapping("/authorization")
    public FileResponseVo authentication(String token, Boolean refresh, String method) {
        Map<String,Object> params = new HashMap<>(4);
        params.put("user",getUser().getUsername());
        params.put("token",token);
        params.put("refresh",refresh);
        params.put("method",method);
        boolean isAuthenticated = giteeAdapter.authorizer(params);
        if (isAuthenticated){
            return FileResponseVo.success("accredit successful!");
        }
        return FileResponseVo.fail("error!");
    }

}
