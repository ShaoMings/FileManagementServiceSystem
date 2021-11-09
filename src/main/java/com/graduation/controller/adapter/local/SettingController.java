package com.graduation.controller.adapter.local;

import cn.hutool.core.util.StrUtil;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.ModifyUserVo;
import com.graduation.service.UserRoleService;
import com.graduation.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Pattern;

/**
 * Description 用户信息控制器
 *
 * @author shaoming
 * @date 2021/8/17 21:45
 * @since 1.0
 */
@Controller
@RequestMapping("/settings")
public class SettingController extends BaseController {

    private static final Integer MAX_NAME_LENGTH = 100;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 个人信息页面
     *
     * @param model model对象
     * @return 页面
     */
    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("user", userService.getById(getUser().getId()));
        Integer role = userRoleService.getUserRole(getUser().getId());
        model.addAttribute("role",role);
        return "settings/user";
    }


    /**
     * 修改用户信息
     *
     * @param user        用户对象
     * @return 响应对象
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public FileResponseVo editUser(ModifyUserVo user) {
        if (StrUtil.isBlank(user.getName()) || user.getName().length() > MAX_NAME_LENGTH) {
            return FileResponseVo.fail("昵称不能为空且在100字符以内");
        }
        User userResult = userService.getById(user.getId());
        userResult.setNickName(user.getName());
        userResult.setGender(user.getGender());
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        if (StrUtil.isBlank(user.getEmail()) || !regex.matcher(user.getEmail()).matches()) {
            return FileResponseVo.fail("邮箱不能为空且格式必须正确");
        }else {
            userResult.setEmail(user.getEmail());
        }
        if (StrUtil.isNotBlank(user.getNewPassword())) {
            if (StrUtil.isBlank(user.getOldPassword()) || user.getOldPassword().length() > 16) {
                return FileResponseVo.fail("请输入原密码");
            }
            if (user.getNewPassword().length() > 16 || user.getNewPassword().length() < 6) {
                return FileResponseVo.fail("新密码必须在6-16字符之间");
            }
            if (userResult.getPassword().equals(new Md5Hash(user.getOldPassword(), userResult.getCredentialsSalt()).toString())) {
                userResult.setPassword(new Md5Hash(user.getNewPassword(), userResult.getCredentialsSalt()).toString());
            } else {
                return FileResponseVo.fail("原密码错误");
            }
        }
        if (userService.modifyUser(userResult)) {
            return FileResponseVo.success("信息修改成功,请重新登录!");
        }
        return FileResponseVo.fail("修改失败");
    }


}
