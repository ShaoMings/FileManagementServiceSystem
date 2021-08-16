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

    private String url;
    private String md5;
    private String path;
    private String domain;
    private String scene;
    private String scenes;
    private String retmsg;
    private int retcode;
    private String src;
}
