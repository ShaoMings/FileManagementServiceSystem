package com.graduation.model.vo;

import lombok.Data;

/**
 * Description 用于获取到的文件或文件夹的信息封装
 *
 * @author shaoming
 * @date 2021/8/17 09:25
 * @since 1.0
 */
@SuppressWarnings("all")
@Data
public class FileInfoVo {
    private Boolean is_dir;
    private String md5;
    private String mTime;
    private String name;
    private String path;
    private String size;
    private String peerAddr;
    private Integer open;
}
