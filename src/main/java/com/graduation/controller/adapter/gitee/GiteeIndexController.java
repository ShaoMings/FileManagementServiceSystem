package com.graduation.controller.adapter.gitee;

import com.graduation.controller.BaseController;
import com.graduation.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/10/15 17:26
 * @since 1.0
 */
@Controller
@RequestMapping("/repo/gite")
public class GiteeIndexController extends BaseController {

    @Autowired
    private RedisUtils redisUtils;

    /**
     *  授权回调地址
     * @param code 授权码
     * @return 视图
     */
    @RequestMapping("/code")
    public String code(String code, @RequestParam(name = "info",required = false) String clientInfo, Model model) {
        if (!redisUtils.hasKey(getUser().getUsername() + "-auth_code")){
            if (StringUtils.isNotBlank(code)) {
                redisUtils.set(getUser().getUsername() + "-auth_code", code, 60*60*24);
            }
        }
        if (StringUtils.isNotBlank(clientInfo)){
            String clientId = clientInfo.substring(0,clientInfo.indexOf("@"));
            String clientSecret = clientInfo.substring(clientInfo.indexOf("@")+1);
            model.addAttribute("clientId",clientId);
            model.addAttribute("clientSecret",clientSecret);
        }
        return "repo/gitee-auth";
    }

    @RequestMapping("/auth")
    public String index() {
        return "repo/gitee-auth";
    }
}
