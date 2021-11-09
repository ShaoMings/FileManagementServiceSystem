package com.graduation.controller.adapter.local;

import com.graduation.utils.AesUtils;
import com.graduation.utils.CommonUtils;
import com.graduation.utils.RedisUtils;
import com.graduation.utils.TokenUtils;
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
            return "check";
        } else {
            return "redirect:/error/outmoded";
        }
    }
}
