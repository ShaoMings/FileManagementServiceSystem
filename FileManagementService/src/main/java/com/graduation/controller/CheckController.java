package com.graduation.controller;

import com.graduation.utils.AesUtils;
import com.graduation.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description 提取码验证
 *
 * @author shaoming
 * @date 2021/9/16 12:04
 * @since 1.0
 */
@Controller
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/code")
    public String checkCode(String code, Model model) throws Exception {
        code = code.replaceAll(" ", "+");
        String path = AesUtils.decrypt(code);
        String groupFilePath = path.substring(path.indexOf("/", path.indexOf("/") + 1), path.lastIndexOf("@"));
        String username = path.substring(0, path.indexOf("/"));
        boolean isNoOverdue = redisUtils.hasKey("token-" + username + groupFilePath);
        if (isNoOverdue) {
            model.addAttribute("code", code);
            return "check";
        } else {
            return "redirect:/error/outmoded";
        }
    }
}
