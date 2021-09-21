package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.mapper.UserMapper;
import com.graduation.model.pojo.User;
import com.graduation.model.pojo.UserRole;
import com.graduation.mapper.UserRoleMapper;
import com.graduation.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    UserService userService;

    @Override
    public Integer getUserRole(Integer userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserRole> list = this.list(queryWrapper);
        UserRole userRole = list.get(0);
        return userRole.getRoleId();
    }

    @Override
    public boolean isExistRecordByUserId(Integer userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserRole> list = this.list(queryWrapper);
        return list.size() > 0;
    }

    @Override
    public List<User> getLowerLevelUserByRoleId( Integer roleId,Integer page,Integer limit) {
        switch (roleId) {
            case 1: {
                return getUserList(Arrays.asList(2, 3, 4),page,limit);
            }
            case 2: {
                return getUserList(Arrays.asList(3, 4),page,limit);
            }
            case 3: {
                return getUserList(Arrays.asList(4),page,limit);
            }
            default:
                break;
        }
        return null;
    }

    @Override
    public Integer getLowerLevelUserCountByRoleId(Integer roleId) {
        switch (roleId) {
            case 1: {
                return getUserCountByRoleIds(Arrays.asList(2, 3, 4));
            }
            case 2: {
                return getUserCountByRoleIds(Arrays.asList(3, 4));
            }
            case 3: {
                return getUserCountByRoleIds(Arrays.asList(4));
            }
            default:
                break;
        }
        return null;
    }

    @Override
    public boolean updateUserRoleByUserId(Integer userId,Integer roleId) {
        LambdaUpdateWrapper<UserRole> userRoleUpdateWrapper = Wrappers.<UserRole>lambdaUpdate()
                .eq(UserRole::getUserId,userId)
                .set(UserRole::getRoleId,roleId);
        return this.update(userRoleUpdateWrapper);
    }


    private List<User> getUserList(List<Integer> lowerLevelRoleIds,Integer page,Integer limit) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", lowerLevelRoleIds);
        Page<UserRole> userRolePage = new Page<>(page,limit);
        this.page(userRolePage,queryWrapper);
        List<UserRole> list = userRolePage.getRecords();
        List<Integer> userIds = new ArrayList<>();
        list.forEach(r -> userIds.add(r.getUserId()));
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("id", userIds);
        return userService.list(userQueryWrapper);
    }

    private Integer getUserCountByRoleIds(List<Integer> lowerLevelRoleIds) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", lowerLevelRoleIds);
        List<UserRole> list = this.list(queryWrapper);
        List<Integer> userIds = new ArrayList<>();
        list.forEach(r->userIds.add(r.getUserId()));
        return userService.getBaseMapper().selectBatchIds(userIds).size();
    }
}
