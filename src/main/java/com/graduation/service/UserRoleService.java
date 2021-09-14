package com.graduation.service;

import com.graduation.model.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
