package com.graduation.controller;

import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UserLoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Description 登录登出控制器
 *
 * @author shaoming
 * @date 2021/8/17 10:07
 * @since 1.0
 */
@Controller
public class SignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignController.class);

    /**
     *  登录页视图跳转
     * @return 登录界面
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    /**
     *  登录操作
     * @param user 前端入参
     * @return 响应对象
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public FileResponseVo doLogin(UserLoginVo user, HttpSession session) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword(), true);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            LOGGER.info("用户:{} 登录!", user.getAccount());
            // session 存放用户
            session.setAttribute("isLogin",true);
            session.setAttribute("user",user.getAccount());
            session.setMaxInactiveInterval(1800);
            return FileResponseVo.success();
        }catch (IncorrectCredentialsException e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return FileResponseVo.fail("密码错误");
        }catch (UnknownAccountException e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return FileResponseVo.fail("用户不存在");
        }catch (Exception e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return FileResponseVo.fail("系统异常");
        }
    }


    /**
     *  登出操作
     * @return 登录界面
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        UserLoginVo user = new UserLoginVo();
        BeanUtils.copyProperties(subject.getPrincipals().getPrimaryPrincipal(),user);
        LOGGER.info("用户:{} 退出登录!",user.getAccount());
        subject.logout();
        return "redirect:/login";
    }

}
