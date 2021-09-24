package com.graduation.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description 用于服务层与控制层的页面数据传输对象
 *
 * @author shaoming
 * @date 2021/8/17 09:53
 * @since 1.0
 */
@Data
public class PageInfoDto<T> implements Serializable {
    private static final long SERIAL_VERSION_UID = 1L;

    private int state;
    private String msg;
    private Long total;
    private List<T> data;

}
