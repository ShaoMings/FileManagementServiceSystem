package com.graduation.jcr.model.dto;

import com.graduation.utils.FileSizeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 用于jcr文件及文件夹 信息封装类
 *
 * @author shaoming
 * @date 2021/10/20 10:11
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JcrContentTreeDto {
    /** 文件相对路径 */
    private String path;
    /** 文件名 */
    private String name;
    /** 文件大小  文件夹为0*/
    private Long size;
    /** 单位化后的文件大小*/
    private String file_size;
    /** 是否为文件夹*/
    private Boolean is_dir;

}
