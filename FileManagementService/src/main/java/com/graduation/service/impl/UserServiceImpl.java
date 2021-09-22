package com.graduation.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.graduation.model.pojo.User;
import com.graduation.mapper.UserMapper;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UserDirStatusVo;
import com.graduation.service.FileService;
import com.graduation.service.PeersService;
import com.graduation.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.utils.Constant;
import com.graduation.utils.FileSizeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private FileService fileService;

    @Autowired
    PeersService peersService;

    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.list(queryWrapper).get(0);
    }

    @Override
    public User getUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean modifyUser(User user) {
        return updateById(user);
    }

    @Override
    public boolean removeUsersDirByUserIds(String peersUrl, Integer[] userIds) {
        List<String> userNames = new ArrayList<>();
        for (Integer id : userIds) {
            userNames.add(this.baseMapper.getUserName(id));
        }
        for (String name : userNames) {
            fileService.deleteDir(peersUrl, name);
        }
        return true;
    }

    @Override
    public boolean updatePeersLeftDiskSpaceByRemoveUserIds(List<User> users) {
        for (User user : users) {
            Double totalDiskSpace = user.getTotalDiskSpace();
            Double peersLeftSpace = peersService.getPeersLeftSpace(user.getPeersid());
            peersService.updatePeersLeftSpace(user.getPeersid(), peersLeftSpace + totalDiskSpace);
        }
        return true;
    }

    @Override
    public Double getUserLeftDiskSpace(Integer userId) {
        return this.getById(userId).getLeftDiskSpace();
    }

    @Override
    public Double getUserTotalDiskSpace(Integer userId) {
        return this.getById(userId).getTotalDiskSpace();
    }

    @Override
    public boolean modifyUserTotalDiskSpaceByUserId(Integer userId, Double size) {
        User user = this.getById(userId);
        Double totalDiskSpace = user.getTotalDiskSpace();
        Double leftDiskSpace = user.getLeftDiskSpace();
        // 修改的空间大小不能比剩余空间小
        if (FileSizeConverter.compareDouble(size,leftDiskSpace)>0) {
            double sub;
            if (FileSizeConverter.compareDouble(totalDiskSpace,size)>0){
                // 减少用户空间
                sub = totalDiskSpace - size;
                boolean a = this.updateUserLeftDiskSpace(userId, leftDiskSpace - sub);
                boolean b = this.updateUserTotalDiskSpace(userId, size);
                return a&b;
            }else if (FileSizeConverter.compareDouble(totalDiskSpace,size)<0){
                // 增加用户空间
                sub = size - totalDiskSpace;
                boolean a = this.updateUserLeftDiskSpace(userId, leftDiskSpace + sub);
                boolean b = this.updateUserTotalDiskSpace(userId, size);
                return a&b;
            }
        }
        return false;
    }

    @Override
    public boolean updateUserTotalDiskSpace(Integer userId, Double size) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userId);
        updateWrapper.set("total_disk_space", size);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateUserLeftDiskSpace(Integer userId, Double leftSpace) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userId);
        updateWrapper.set("left_disk_space", leftSpace);
        return this.update(updateWrapper);
    }

    @Override
    public boolean userUploadFileToUpdateDiskSpace(String peersUrl, Integer userId, String username, Long fileSize) {
        String json = HttpUtil.get(peersUrl + Constant.API_USER_STATUS + "?userPath=" + username);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            JSONObject data = JSONUtil.parseObj(jsonObject.get("data"));
            // 用户初始总存储空间大小
            Double userTotalDiskSpace = this.getUserTotalDiskSpace(userId);
            double userLeftDiskSpace;
            Object tmpSize = data.get("size");
            if (tmpSize instanceof Integer) {
                // 用户存储空间使用量
                int sumOfUsedSize = (Integer) tmpSize + fileSize.intValue();
                // 用户真实剩余存储空间
                userLeftDiskSpace = userTotalDiskSpace - (double) sumOfUsedSize;
            } else {
                // 用户存储空间使用量
                long sumOfUsedSize = (Long) tmpSize + fileSize;
                // 用户真实剩余存储空间
                userLeftDiskSpace = userTotalDiskSpace - (double) sumOfUsedSize;
            }
            if (userLeftDiskSpace > 0) {
                return this.updateUserLeftDiskSpace(userId, userLeftDiskSpace);
            }
        }
        return false;
    }


    @Override
    public boolean userDeleteFileToUpdateDiskSpace(String peersUrl, Integer userId, String username) {
        String json = HttpUtil.get(peersUrl + Constant.API_USER_STATUS + "?userPath=" + username);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            JSONObject data = JSONUtil.parseObj(jsonObject.get("data"));
            // 用户存储空间使用量
            Integer sumOfUsedSize = (Integer) data.get("size");
            // 用户初始总存储空间大小
            Double userTotalDiskSpace = this.getUserTotalDiskSpace(userId);
            // 用户真实剩余存储空间
            double userLeftDiskSpace = userTotalDiskSpace - sumOfUsedSize.doubleValue();
            if (userLeftDiskSpace > 0) {
                return this.updateUserLeftDiskSpace(userId, userLeftDiskSpace);
            }
        }
        return false;
    }


}
