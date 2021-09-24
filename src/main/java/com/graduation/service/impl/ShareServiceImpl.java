package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.graduation.model.pojo.File;
import com.graduation.model.pojo.Share;
import com.graduation.mapper.ShareMapper;
import com.graduation.service.FileService;
import com.graduation.service.ShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shaoming
 * @since 2021-09-16
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {

    @Autowired
    private FileService fileService;

    @Override
    public boolean downloadCountPlusById(Integer shareId,Integer newCount) {
        UpdateWrapper<Share> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",shareId);
        updateWrapper.set("download_count",newCount);
        return this.update(updateWrapper);
    }

    @Override
    public boolean readCountPlusById(Integer shareId, Integer newCount) {
        UpdateWrapper<Share> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",shareId);
        updateWrapper.set("read_count",newCount);
        return this.update(updateWrapper);
    }

    @Override
    public List<File> getSevenDayShareFiles(Date startTime, Date endTime) {
        LambdaQueryWrapper<Share> lq = Wrappers.lambdaQuery();
        lq.between(Share::getShareTime,startTime,endTime);
        List<Share> shareList = this.list(lq);
        List<File> fileList = new ArrayList<>();
        shareList.forEach(s -> fileList.add(fileService.getById(s.getFileId())));
        return fileList;
    }

    @Override
    public List<Share> getSevenDayShareFilesRecord(Date startTime, Date endTime) {
        LambdaQueryWrapper<Share> lq = Wrappers.lambdaQuery();
        lq.between(Share::getShareTime,startTime,endTime);
        List<Share> list = this.list(lq);
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<Share> getSevenDayShareFilesRecordByUserName(Date startTime, Date endTime, String username) {
        LambdaQueryWrapper<Share> lq = Wrappers.lambdaQuery();
        lq.eq(Share::getSharerUsername,username);
        lq.between(Share::getShareTime,startTime,endTime);
        List<Share> list = this.list(lq);
        Collections.reverse(list);
        return list;
    }

    @Override
    public boolean privateFileToRemoveRecordByFileId(Integer fileId) {
        QueryWrapper<Share> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_id",fileId);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean privateFilesToRemoveRecordsByFileIdList(List<Integer> fileIds) {
        QueryWrapper<Share> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("file_id",fileIds);
        return this.remove(queryWrapper);
    }

}
