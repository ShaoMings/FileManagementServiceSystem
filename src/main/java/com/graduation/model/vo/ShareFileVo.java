package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 文件分享信息封装类
 *
 * @author shaoming
 * @date 2021/8/30 13:03
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShareFileVo {
    private String path;
    private String filename;
    private Integer days;

}
