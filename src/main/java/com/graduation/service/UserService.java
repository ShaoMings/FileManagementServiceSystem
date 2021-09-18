package com.graduation.service;

import com.graduation.model.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUserName(String username);

    /**
     *  通过邮箱查找用户对象
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     *  更新用户信息
     * @param user 用户对象
     * @return 是否更新成功
     */
    boolean modifyUser(User user);


    /**
     * 通过指定用户的用户名删除用户根目录
     * @param peersUrl 集群url
     * @param userIds 用户ids
     * @return 是否删除成功
     */
    boolean removeUsersDirByUserIds(String peersUrl,Integer[] userIds);

}
