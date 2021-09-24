package com.graduation.model.vo;

import lombok.Data;

/**
 * Description 上传结果响应对象
 *
 * @author shaoming
 * @date 2021/8/16 22:52
 * @since 1.0
 */
@Data
public class UploadResultVo {
    /** 文件完整访问路径 包含服务地址*/
    private String url;
    private String md5;
    /** 文件存放路径 包含组名(如果设置了的话)*/
    private String path;
    private String domain;
    private String scene;
    private String scenes;
    private String retmsg;
    private int retcode;
    private String src;
}
