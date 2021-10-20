package com.graduation.controller.adapter.gitee;

import com.graduation.controller.BaseController;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 用于管理redis token的控制器
 *
 * @author shaoming
 * @date 2021/10/19 10:33
 * @since 1.0
 */
@RestController
@RequestMapping("/repo/gite")
public class TokenController extends BaseController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/token")
    public FileResponseVo getToken(){
        if (redisUtils.hasKey(getUser().getUsername() + "-auth_token")) {
            String token = (String) redisUtils.get(getUser().getUsername() + "-auth_token");
            return FileResponseVo.success(token);
        }
        return FileResponseVo.fail("cannot get user gitee token!");
    }
}
