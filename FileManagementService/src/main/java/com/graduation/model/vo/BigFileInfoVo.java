package com.graduation.model.vo;

import lombok.Data;

/**
 * Description 大文件上传回调信息封装类
 *
 * @author shaoming
 * @date 2021/9/19 下午12:19
 * @since 1.0
 */
@Data
public class BigFileInfoVo {
    private String name;
    private String rename;
    private String path;
    private String md5;
    private Long size;
    private String[] peers;
    private String scene;
    private Long timeStamp;
    private Integer offset;
}
