package com.graduation.model.vo;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * Description API接口响应格式
 *
 * @author shaoming
 * @date 2021/8/17 14:53
 * @since 1.0
 */
@Data
public class ApiResultVo implements Serializable {
    private static final long serialVersionUID = -2561810964453277221L;
    private String status;
    private String message;
    private JSONObject data;
}
