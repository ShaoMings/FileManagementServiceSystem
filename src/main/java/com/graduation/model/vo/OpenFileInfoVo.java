package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 公开文件信息封装类
 *
 * @author shaoming
 * @date 2021/9/17 09:11
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpenFileInfoVo {
    private Integer shareId;
    private String fileName;
    private String filePath;
    private String sharerUsername;
    private String sharer;
    private String size;
    private String time;
    private Integer download;
    private Integer read;
}