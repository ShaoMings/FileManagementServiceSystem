package com.graduation.controller.adapter.local;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduation.model.pojo.User;
import com.graduation.service.UserRoleService;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Description index
 *
 * @author shaoming
 * @date 2021/8/17 16:15
 * @since 1.0
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    private  UserRoleService userRoleService;

    /**
     * 首页视图控制
     * @param model 模型对象
     * @return 首页
     */
    @GetMapping(value = {"","/","/index"})
    public String index(Model model){
        User user = getUser();
        User tmp = new User();
        tmp.setId(user.getId());
        tmp.setLastLoginTime(new Date());
        userService.updateById(tmp);
        Integer role = userRoleService.getUserRole(user.getId());
        model.addAttribute("role",role);
        model.addAttribute("user",user);
        return "index";
    }

    @GetMapping("/getUser")
    @ResponseBody
    public String getLoginUser() throws JsonProcessingException {
        User user = getUser();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }


}
