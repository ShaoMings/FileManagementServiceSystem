package com.graduation.service;

import com.graduation.model.pojo.User;
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

    /**
     * 删除用户后归还存储空间到集群中
     * @param users 用户
     * @return 是否归还成功
     */
    boolean updatePeersLeftDiskSpaceByRemoveUserIds(List<User> users);

    /**
     * 获取用户剩余存储空间
     * @param userId 用户id
     * @return 剩余存储空间
     */
    Double getUserLeftDiskSpace(Integer userId);

    /**
     * 获取用户总存储空间
     * @param userId 用户id
     * @return 总存储空间
     */
    Double getUserTotalDiskSpace(Integer userId);


    /**
     * 修改用户的总存储空间大小
     * @param userId 用户id
     * @param size 空间大小
     * @return 是否修改成功
     */
    boolean modifyUserTotalDiskSpaceByUserId(Integer userId,Double size);


    /**
     * 更新用户总存储空间
     * @param userId 用户id
     * @param size 空间
     * @return 是否更新成功
     */
    boolean updateUserTotalDiskSpace(Integer userId,Double size);


    /**
     * 更新用户剩余存储空间
     * @param userId 用户id
     * @param leftSpace 剩余空间
     * @return 是否更新成功
     */
    boolean updateUserLeftDiskSpace(Integer userId,Double leftSpace);

    /**
     * 用户上传之前检查剩余存储空间是否足够
     * @param peersUrl 集群url
     * @param userId 用户id
     * @param username 用户名
     * @param fileSize 上传的文件总大小
     * @return 是否更新成功
     */
    boolean userUploadFileToUpdateDiskSpace(String peersUrl,Integer userId,String username,Long fileSize);

    /**
     * 用户删除之后更新剩余存储空间
     * @param peersUrl 集群url
     * @param userId 用户id
     * @param username 用户名
     * @return 是否更新成功
     */
    boolean userDeleteFileToUpdateDiskSpace(String peersUrl,Integer userId,String username);
}
