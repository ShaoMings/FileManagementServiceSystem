package com.graduation.controller.adapter.local;

import com.graduation.model.pojo.Peers;
import com.graduation.model.pojo.User;
import com.graduation.model.pojo.UserRole;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.InstallVo;
import com.graduation.model.vo.UserLoginVo;
import com.graduation.model.vo.UserSignUpVo;
import com.graduation.service.PeersService;
import com.graduation.service.UserRoleService;
import com.graduation.service.UserService;
import com.graduation.utils.FileSizeConverter;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Description 登录登出控制器
 *
 * @author shaoming
 * @date 2021/8/17 10:07
 * @since 1.0
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignController.class);

    private final UserService userService;

    private final PeersService peersService;

    private final UserRoleService userRoleService;



    /**
     * 登录页视图跳转
     *
     * @return 登录界面
     */
    @RequestMapping("/login")
    public String login(Model model) {
        String msg = (String) model.getAttribute("msg");
        if (!"".equals(msg)) {
            model.addAttribute("msg", "");
        }
        return "login";
    }


    /**
     * 登录操作
     *
     * @param user 前端入参
     * @return 响应对象
     */
    @RequestMapping("/doLogin")
    public String doLogin(UserLoginVo user, HttpSession session, Model model) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword(), false);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            LOGGER.info("用户:{} 登录!", user.getAccount());
            // session 存放用户
            session.setAttribute("isLogin", true);
            session.setAttribute("username", user.getAccount());
            session.setMaxInactiveInterval(3 * 60 * 60);
            return "redirect:/";
        } catch (Exception e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            model.addAttribute("msg", "用户名或密码有误,请检查后再试!");
            return "redirect:login";
        }
    }


    @RequestMapping("/doSignUp")
    @ResponseBody
    public FileResponseVo register(UserSignUpVo user, HttpServletResponse response) throws IOException {
        if (userService.getUserByUserName(user.getAccount())!=null){
            return FileResponseVo.fail("账号已存在");
        }
        if (user.getPassword().equals(user.getConfirm())) {
            List<Peers> peers = peersService.getAllPeers();
            if (peers.size()>0){
                boolean isSign = false;
                for (Peers p : peers) {
                    try {
                        if (peersService.checkPeersAddressIsUseful(p.getServerAddress() + "/" + p.getGroupName())) {
                            Double peersLeftSpace = peersService.getPeersLeftSpace(p.getId());
                            // 默认新用户5GB存储空间
                            Double initSpace = FileSizeConverter.getLengthAutoCalToByte("5GB");
                            if (FileSizeConverter.compareDouble(peersLeftSpace, initSpace) > 0) {
                                peersLeftSpace -= initSpace;
                                peersService.updatePeersLeftSpace(p.getId(), peersLeftSpace);
                                InstallVo installVo = new InstallVo();
                                User voUser = installVo.getUser(user.getPassword(), user.getAccount(), user.getEmail(), user.getName());
                                voUser.setPeersid(p.getId());
                                voUser.setAge(18);
                                voUser.setTotalDiskSpace(initSpace);
                                voUser.setLeftDiskSpace(initSpace);
                                isSign = userService.save(voUser);
                                // 设置用户角色 默认普通用户
                                Integer userId = voUser.getId();
                                boolean isExist = userRoleService.isExistRecordByUserId(userId);
                                if (!isExist) {
                                    userRoleService.save(new UserRole(null, userId, 4));
                                }
                            }
                        }
                    } catch (Exception ignored) {}
                }
                if (isSign) {
                    return FileResponseVo.success();
                }
            }else {
                // 没有集群 跳转到集群添加页面
                response.sendRedirect("/install");
                return FileResponseVo.fail("没有可用集群,请添加集群!");
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
