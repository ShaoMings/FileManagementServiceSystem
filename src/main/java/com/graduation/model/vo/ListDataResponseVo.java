package com.graduation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description 用于列表数据的封装类
 *  code 0表示成功 其他失败
 *  msg 可自定义
 *  count为返回记录条数 建议分页返回 不建议全部返回后在前端分页
 *  data 为表格数据主体封装类
 *
 * @author shaoming
 * @date 2021/9/15 09:57
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListDataResponseVo<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}
