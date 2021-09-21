package com.graduation.service;

import com.graduation.model.pojo.User;
import com.graduation.model.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 通过用户id获取用户角色
     * @param userId 用户id
     * @return 角色
     */
    Integer getUserRole(Integer userId);

    /**
     *  通过用户id查询是否存在该用户的角色记录
     * @param userId 用户id
     * @return true 存在 false 不存在
     */
    boolean isExistRecordByUserId(Integer userId);


    /**
     * 通过角色id获取 该用户管理的所有下级用户
     * @param roleId 角色id  只接受 1 或 2 或 3 因为 4为普通用户 不具备管理用户的权限
     * @param page 当前页
     * @param  limit 返回条数
     * @return  用户列表
     */
    List<User> getLowerLevelUserByRoleId(Integer roleId,Integer page,Integer limit);

    /**
     * 通过角色id获取 该用户管理的所有下级用户总数
     * @param roleId 角色id  只接受 1 或 2 或 3 因为 4为普通用户 不具备管理用户的权限
     * @return  用户列表
     */
    Integer getLowerLevelUserCountByRoleId(Integer roleId);

    /**
     *  通过用户id更新用户角色id
     * @param userId 用户id
     * @param  roleId 要更新的角色
     * @return 是否更新成功
     */
    boolean updateUserRoleByUserId(Integer userId,Integer roleId);

}
