package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description TODO
 *
 * @author shaoming
 * @date 2021/9/12 20:03
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceiveResponseVo {
    private Integer code;
    private String msg;
    private Integer count;
    private List<EmailReceiveVo> data;
}
