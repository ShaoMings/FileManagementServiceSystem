package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/20 下午4:34
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserOpenFileInfoVo {
    private Integer shareId;
    private String fileName;
    private String filePath;
    private String fileMd5;
    private String sharerUsername;
    private String sharer;
    private String size;
    private String time;
    private Integer download;
    private Integer read;
}