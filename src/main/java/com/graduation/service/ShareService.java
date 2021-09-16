package com.graduation.service;

import com.graduation.model.pojo.File;
import com.graduation.model.pojo.Share;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-09-16
 */
public interface ShareService extends IService<Share> {

    /**
     * 通过分享的文件的表id对下载量加1
     * @param shareId id
     * @param newCount 加1后的总数
     */
    void downloadCountPlusById(Integer shareId,Integer newCount);

    /**
     * 通过分享的文件的表的id对阅读量加1
     * @param shareId id
     * @param newCount 加1后的总数
     */
    void readCountPlusById(Integer shareId,Integer newCount);

    /**
     * 通过指定日期时间段查询该时间段的记录
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 该时间段分享的文件记录
     */
    List<File> getSevenDayShareFiles(Date startTime, Date endTime);

    /**
     * 通过文件id移除公开记录
     * @param fileId 文件id
     * @return 是否删除成功
     */
    boolean privateFileToRemoveRecordByFileId(Integer fileId);

}
