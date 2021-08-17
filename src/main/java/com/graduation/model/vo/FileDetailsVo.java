package com.graduation.model.vo;

import lombok.Data;

import java.util.List;

/**
 * Description 文件详细信息封装
 *
 * @author shaoming
 * @date 2021/8/17 14:21
 * @since 1.0
 */
@Data
public class FileDetailsVo {
    private String url;
    private String path;
    private String size;
    private String name;
    private String md5;
    private String scene;
    private String timeStamp;
    private List<String> peers;
}
