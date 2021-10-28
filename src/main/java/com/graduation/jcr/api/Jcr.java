package com.graduation.jcr.api;

import com.graduation.jcr.model.dto.JcrContentTreeDto;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author shaoming
 */
public interface Jcr {

    /**
     * 初始化仓库  一般授权后执行 初始化已授权的仓库信息到jcr中
     * @param repository 初始化的jcr仓库名称
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否初始化成功
     */
    default boolean initializer(String repository,String api,Map<String, Object> params,String method){
        return false;
    }

    /**
     * 授权器
     * @param api 请求api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否授权
     */
    default boolean authorizer(String api,Map<String, Object> params,String method){
        return false;
    }
    /**
     * 同步器
     * @param api 请求api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否同步成功
     */
    default boolean synchronizer(String api,Map<String, Object> params,String method){
        return false;
    }

    /**
     * 检测器
     * @param api 请求api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否有更新
     */
    default boolean detector(String api,Map<String, Object> params,String method){
        return false;
    }

    /**
     *  添加文件夹
     * @param absolute 文件夹的绝对路径
     * @return 是否添加成功
     */
    default boolean addDirectory(String absolute){
        return false;
    }

    /**
     * 添加文件夹
     * @param absolute 文件夹的绝对路径
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否添加成功
     */
    default boolean addDirectory(String absolute, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 删除文件夹
     * @param absolute 文件夹的绝对路径
     * @return 是否删除成功
     */
    default boolean removeDirectory(String absolute){
        return false;
    }

    /**
     * 删除文件夹
     * @param absolute 文件夹的绝对路径
     * @param params 自定义参数
     * @return 是否删除成功
     */
    default boolean removeDirectory(String absolute,Map<String,Object> params){
        return false;
    }

    /**
     * 删除文件夹
     * @param absolute 文件夹的绝对路径
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否删除成功
     */
    default boolean removeDirectory(String absolute, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 重命名文件夹
     * @param absolute 文件夹的绝对路径
     * @param newName 文件夹新名称
     * @return 是否重命名成功
     */
    default boolean renameDirectory(String absolute,String newName){
        return false;
    }

    /**
     * 重命名文件夹
     * @param absolute 文件夹的绝对路径
     * @param newName 文件夹新名称
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否重命名成功
     */
    default boolean renameDirectory(String absolute,String newName, String api, Map<String,Object> params,String method){
        return false;
    }


    /**
     * 添加文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @param content 文件源
     * @return 是否添加成功
     */
    default boolean addFile(String dirAbsolute, String filename, InputStream content){
        return false;
    }

    /**
     * 添加文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @param content 文件源
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否添加成功
     */
    default boolean addFile(String dirAbsolute, String filename, InputStream content, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 添加文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @param content 文件源
     * @return 是否添加成功
     */
    default boolean addFile(String dirAbsolute, String filename, String content){
        return false;
    }

    /**
     * 添加文件
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否添加成功
     */
    default boolean addFile(String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 添加文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @param content 文件源
     * @return 是否添加成功
     */
    default boolean addFile(String dirAbsolute, String filename, Object content){
        return false;
    }

    /**
     * 添加文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @param content 文件源
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否添加成功
     */
    default boolean addFile(String dirAbsolute, String filename, Object content, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 删除文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @return 是否删除成功
     */
    default boolean deleteFile(String dirAbsolute, String filename){
        return false;
    }

    /**
     * 删除文件
     * @param absolute 文件的绝对路径
     * @param params 自定义参数
     * @return 是否删除成功
     */
    default boolean deleteFile(String absolute,Map<String,Object> params){
        return false;
    }

    /**
     * 删除文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否删除成功
     */
    default boolean deleteFile(String dirAbsolute, String filename, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 重命名文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param oldName 旧文件名
     * @param newName 新文件名
     * @return 是否重命名成功
     */
    default boolean renameFile(String dirAbsolute, String oldName,String newName){
        return false;
    }

    /**
     * 重命名文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param oldName 旧文件名
     * @param newName 新文件名
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否重命名成功
     */
    default boolean renameFile(String dirAbsolute, String oldName,String newName, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 更新文件内容
     * @param content 新文件源
     * @return 是否更新成功
     */
    default boolean updateFile(InputStream content){
        return false;
    }

    /**
     * 更新文件内容
     * @param content 新文件源
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否更新成功
     */
    default boolean updateFile(InputStream content, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 更新文件内容
     * @param content 新文件源
     * @return 是否更新成功
     */
    default boolean updateFile(String content){
        return false;
    }

    /**
     * 更新文件内容
     * @param content 新文件源
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否更新成功
     */
    default boolean updateFile(String content, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 更新文件内容
     * @param content 新文件源
     * @return 是否更新成功
     */
    default boolean updateFile(Object content){
        return false;
    }

    /**
     * 更新文件内容
     * @param content 新文件源
     * @param api 实际远程操作的api
     * @param params 请求参数
     * @param method 请求方式
     * @return 是否更新成功
     */
    default boolean updateFile(Object content, String api, Map<String,Object> params,String method){
        return false;
    }

    /**
     * 获取文件
     * @param absolute 文件存放的文件夹绝对路径
     * @param beanClass 用于接收文件信息的实体类型
     * @param <T> 实体类型
     * @return 文件
     */
    default <T> T getFile(String absolute,Class<T> beanClass){
        return null;
    }


}
