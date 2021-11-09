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

    /**
     * 通过文件路径查找是否存在该文件公开记录
     *
     * @param filePath 文件路径
     * @return 是否存在
     */
    @Override
    public boolean existsShareByFilePath(String filePath) {
        LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<Share>().eq(Share::getFilePath, filePath);
        return this.list(wrapper).size() == 1;
    }

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

    /**
     * 通过文件Path移除公开记录
     *
     * @param filePath 文件id
     * @return 是否删除成功
     */
    @Override
    public boolean privateFileToRemoveRecordByFilePath(String filePath) {
        QueryWrapper<Share> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_path",filePath);
        return this.remove(queryWrapper);
    }

    /**
     * 通过公开文件Path集合 批量移除公开记录
     *
     * @param filePaths 公开文件id集合
     * @return 是否删除成功
     */
    @Override
    public boolean privateFilesToRemoveRecordsByFilePathList(List<String> filePaths) {
        QueryWrapper<Share> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("file_path",filePaths);
        return this.remove(queryWrapper);
    }

}
