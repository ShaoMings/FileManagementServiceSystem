package com.graduation.service;

import com.graduation.model.pojo.File;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.model.vo.FileInfoVo;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.PictureConvertVo;

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
     * @param groupName 组名
     * @param path 文件path
     * @return 是否删除成功
     */
    boolean deleteFile(String peersUrl,String groupName,String path);

    /**
     *  创建文件夹
     * @param serverAddress 服务地址 包含组名
     * @param path 文件夹路径
     * @return 是否创建成功
     */
    boolean mkdir(String serverAddress,String path);

    /**
     *  删除文件夹
     * @param peersUrl 集群url 包含组名
     * @param path 文件夹路径 不含/files  如/test
     * @return 是否删除成功
     */
    boolean deleteDir(String peersUrl,String path);

    /**
     * 删除数据库对应文件夹中的记录
     * @param pathPrefix 文件存储路径 组名+文件夹路径
     * @return 是否删除成功
     */
    boolean deleteDirFileRecord(String pathPrefix);

    /**
     *  重命名文件或文件夹
     *  @param peersUrl 集群url
     * @param oldPath 旧路径
     * @param newPath 新路径
     * @param path 文件路径
     * @param groupName 组名
     * @param md5 文件md5
     * @return 是否命名成功
     */
    boolean renameFileOrFolder(String peersUrl,String oldPath,String newPath,String path,String groupName,String md5);

    /**
     *  获取单个文件内容细节
     * @param peersUrl 集群url
     * @param md5 文件md5
     * @return  文件信息响应对象
     */
    FileResponseVo getFileDetails(String peersUrl,String md5);

    /**
     * 通过用户id保存文件路径
     * @param id 用户id 唯一
     * @param filePath 组名+服务器存放文件路径
     * @return 是否保存成功
     */
    boolean saveFilePathByUserId(Integer id,String filePath);


    /**
     *  通过文件名关键字获取相关文件
     * @param keyword 关键字
     * @param serverAddress 服务地址 包含组名
     * @return 符合要求的所有文件
     */
    List<FileInfoVo> getFileInfoListByFileKeyword(String serverAddress,String keyword);

    /**
     * 转换图片格式 目前支持png和jpg互转
     * @param fileInfo 文件信息封装类
     * @return 是否转换成功
     */
    boolean convertPictureFile(PictureConvertVo fileInfo);
}
