package com.graduation.controller;


import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.InstallVo;
import com.graduation.model.vo.ManageUserVo;
import com.graduation.model.vo.TableResponseVo;
import com.graduation.service.UserRoleService;
import com.graduation.service.UserService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/username")
    @ResponseBody
    public String getUsername() {
        return getUser().getUsername();
    }


    @RequestMapping("/manage")
    public String userManage() {
        return "manage";
    }

    @RequestMapping("/manageUserList")
    @ResponseBody
    public TableResponseVo<ManageUserVo> getManageUserList() {
        Integer userId = getUser().getId();
        Integer userRoleId = userRoleService.getUserRole(userId);
        List<User> userList = userRoleService.getLowerLevelUserByRoleId(userRoleId);
        List<ManageUserVo> user = new ArrayList<>();
        userList.forEach(u -> {
            Integer id = u.getId();
            Integer roleId = userRoleService.getUserRole(id);
            user.add(new ManageUserVo(id, u.getUsername(), u.getNickName(), u.getPassword(),
                    u.getGender(), u.getAge(), u.getEmail(), u.getPeersid(), roleId));
        });
        return new TableResponseVo<>(0, "", user.size(), user);
    }


    @RequestMapping("/deleteSelected")
    @ResponseBody
    public FileResponseVo deleteSelected(@RequestParam("ids[]") String[] ids) {
        Integer[] userIds = (Integer[]) ConvertUtils.convert(ids, Integer.class);
        boolean isDelAll = userService.removeById(userIds);
        if (isDelAll) {
            return FileResponseVo.success();
        } else {
            return FileResponseVo.fail("删除失败!");
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public FileResponseVo delete(Integer id) {
        boolean isDel = userService.removeById(id);
        if (isDel) {
            return FileResponseVo.success();
        } else {
            return FileResponseVo.fail("删除失败!");
        }
    }

    @RequestMapping("/modify")
    @ResponseBody
    public FileResponseVo modify(ManageUserVo userVo) {
        // 判断密码是否更改
        User user;
        User oldUser = userService.getById(userVo.getId());
        if (oldUser.getPassword().equals(userVo.getPassword())) {
            user = new User();
        }else {
            InstallVo installVo = new InstallVo();
            user = installVo.getUserForEncryptPassword(userVo.getPassword());
        }
        user.setId(userVo.getId());
        user.setUsername(userVo.getUserName());
        user.setEmail(userVo.getEmail());
        user.setNickName(userVo.getNickName());
        user.setAge(userVo.getAge());
        user.setGender(userVo.getGender());
        user.setPeersid(userVo.getPeersId());
        user.setUpdateTime(new Date());
        boolean isModify = userService.modifyUser(user);
        userRoleService.updateUserRoleByUserId(userVo.getId(),userVo.getRoleId());
        if (isModify) {
            return FileResponseVo.success();
        } else {
            return FileResponseVo.fail("修改失败!");
        }
    }


}

