package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 图片格式转换vo
 *
 * @author shaoming
 * @date 2021/8/23 13:33
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PictureConvertVo {
    /** 非必须 基类获取 */
    private Integer userId;
    /** 文件路径 */
    private String path;
    /** 文件名 */
    private String filename;
    /** 非必须 基类获取 */
    private String peerGroupName;
    /** 非必须 基类获取 */
    private String peerAddress;
    /** 转换前类型 */
    private String srcSuffix;
    /** 转换后类型 */
    private String destSuffix;
}
