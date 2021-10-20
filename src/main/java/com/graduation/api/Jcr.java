package com.graduation.api;

import java.io.InputStream;

/**
 * @author shaoming
 */
public interface Jcr {
    /**
     *  添加文件夹
     * @param absolute 文件夹的绝对路径
     * @return 是否添加成功
     */
    default boolean addDirectory(String absolute){
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
     * 重命名文件夹
     * @param absolute 文件夹的绝对路径
     * @param newName 文件夹新名称
     * @return 是否重命名成功
     */
    default boolean renameDirectory(String absolute,String newName){
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
     * @return 是否添加成功
     */
    default boolean addFile(String dirAbsolute, String filename, String content){
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
     * 删除文件
     * @param dirAbsolute 文件存放的文件夹绝对路径
     * @param filename 文件名
     * @return 是否删除成功
     */
    default boolean deleteFile(String dirAbsolute, String filename){
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

}
