package com.graduation.service;

import com.graduation.model.pojo.File;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.model.vo.FileResponseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shaoming
 * @since 2021-08-16
 */
public interface FileService extends IService<File> {

    /**
     *  获取一级目录
     * @param peersGroupName 集群组名
     * @param serverAddress 服务地址
     * @return  文件信息对象列表
     */
    List<FileInfoVo> getParentFile(String peersGroupName,String serverAddress);

    /**
     *  获取指定的目录
     * @param backUrl 回调url
     * @param serverAddress 服务地址
     * @param dir 文件目录
     * @return 文件信息对象列表
     */
    List<FileInfoVo> getDirFile(String backUrl,String serverAddress,String dir);

    /**
     *  删除文件
     * @param peersUrl 集群url
     * @param md5 文件md5
     * @return 是否删除成功
     */
    boolean deleteFile(String peersUrl,String md5);

    /**
     *  获取单个文件内容细节
     * @param peersUrl 集群url
     * @param md5 文件md5
     * @return  文件信息响应对象
     */
    FileResponseVo getFileDetails(String peersUrl,String md5);

}
