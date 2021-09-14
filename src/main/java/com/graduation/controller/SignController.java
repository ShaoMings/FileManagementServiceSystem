package com.graduation.controller;

import com.graduation.model.pojo.Peers;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.InstallVo;
import com.graduation.model.vo.UserLoginVo;
import com.graduation.model.vo.UserSignUpVo;
import com.graduation.service.PeersService;
import com.graduation.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

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


    @Autowired
    private UserService userService;

    @Autowired
    private PeersService peersService;

    /**
     * 登录页视图跳转
     *
     * @return 登录界面
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * 登录操作
     *
     * @param user 前端入参
     * @return 响应对象
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public FileResponseVo doLogin(UserLoginVo user, HttpSession session) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword(), false);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            LOGGER.info("用户:{} 登录!", user.getAccount());
            // session 存放用户
            session.setAttribute("isLogin", true);
            session.setAttribute("user", user.getAccount());
            session.setMaxInactiveInterval(1800);
            return FileResponseVo.success();
        } catch (IncorrectCredentialsException e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return FileResponseVo.fail("密码错误");
        } catch (UnknownAccountException e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return FileResponseVo.fail("用户不存在");
        } catch (Exception e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return FileResponseVo.fail("系统异常");
        }
    }


    @RequestMapping("/doSignUp")
    @ResponseBody
    public FileResponseVo register(UserSignUpVo user) {
        if (user.getPassword().equals(user.getConfirm())) {
            List<Peers> peers = peersService.getAllPeers();
            boolean isSign = false;
            for (Peers p : peers) {
                try {
                    if (peersService.checkPeersAddressIsUseful(p.getServerAddress() + "/" + p.getGroupName())) {
                        Map<String, Object> peersStatus = peersService.getPeersStatus(p.getServerAddress() + "/" + p.getGroupName());
                        String diskFreeSize = (String) peersStatus.get("diskFreeSize");
                        System.out.println("diskFreeSize = " + diskFreeSize);
                        InstallVo installVo = new InstallVo();
                        User voUser = installVo.getUser(user.getPassword(), user.getAccount(), user.getEmail(), user.getName());
                        voUser.setPeersid(p.getId());
                        voUser.setAge(18);
                        isSign = userService.save(voUser);
                    }
                }catch (Exception ignored){

                }

            }
            if (isSign) {
                return FileResponseVo.success();
            }
            return FileResponseVo.fail("注册失败,请联系管理员请检查是否存在可用集群!");
        } else {
            return FileResponseVo.fail("两次密码不一致,请检查后再试!");
        }
    }


    /**
     * 登出操作
     *
     * @return 登录界面
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        UserLoginVo user = new UserLoginVo();
        BeanUtils.copyProperties(subject.getPrincipals().getPrimaryPrincipal(), user);
        LOGGER.info("用户:{} 退出登录!", user.getAccount());
        subject.logout();
        return "redirect:/login";
    }

}
