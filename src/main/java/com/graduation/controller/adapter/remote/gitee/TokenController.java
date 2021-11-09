package com.graduation.controller.adapter.remote.gitee;

import com.graduation.controller.adapter.local.BaseController;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.repo.adapter.TokenProxy;
import com.graduation.utils.AesUtils;
import com.graduation.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description 用于管理redis token的控制器
 *
 * @author shaoming
 * @date 2021/10/19 10:33
 * @since 1.0
 */
@Controller
@RequestMapping("/repo/gite")
public class TokenController extends BaseController {

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("/check/code")
    public String checkCode(String code, Model model) throws Exception {
        code = code.replaceAll(" ", "+");
        String path = AesUtils.decrypt(code);
        int count = path.length() - path.replaceAll("/","").length();
        String filePath;
        if (count>1 && path.startsWith("/")){
            filePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
        }else {
            filePath = path.substring(path.indexOf("/"),path.lastIndexOf("@"));
        }
        String username = path.substring(0, path.indexOf("/"));
        boolean isNoOverdue;
        if (filePath.startsWith("/")){
            if (filePath.startsWith("/"+username)){
                isNoOverdue = redisUtils.hasKey("token-" + filePath.substring(1));
            }else {
                isNoOverdue = redisUtils.hasKey("token-" + username + filePath);
            }
        }else {
            isNoOverdue = redisUtils.hasKey("token-" + username + filePath);
        }
        if (isNoOverdue) {
            model.addAttribute("code", code);
            return "repo/check";
        } else {
            return "redirect:/error/outmoded";
        }
    }

    @RequestMapping("/token")
    @ResponseBody
    public FileResponseVo getToken(){
        if (redisUtils.hasKey(getUser().getUsername() + "-auth_token")) {
            String token = (String) redisUtils.get(getUser().getUsername() + "-auth_token");
            token = TokenProxy.tokenEncode(token);
            return FileResponseVo.success(token);
        }
        return FileResponseVo.fail("cannot get user gitee token!");
    }
}
