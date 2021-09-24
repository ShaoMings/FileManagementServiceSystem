package com.graduation.model.vo;

import lombok.Data;

/**
 * Description 用户目录文件夹状态封装类
 *
 * @author shaoming
 * @date 2021/9/19 下午3:44
 * @since 1.0
 */
@Data
public class UserDirStatusVo {
    private Integer count;
    private String size;
    private String total;
    private String left;
}
