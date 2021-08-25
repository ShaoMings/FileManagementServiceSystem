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
    private String path;
    private String filename;
    private String peerGroupName;
    private String scene;
    private String peerAddress;
    private String srcSuffix;
    private String destSuffix;
}
