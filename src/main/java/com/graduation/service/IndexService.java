package com.graduation.service;

import java.util.Map;

/**
 * @author shaoming
 */
public interface IndexService {
    /**
     * 获取首页展示的系统状态
     * @param data data
     * @return map
     */
    Map<String, Object> getStatus(Object data);
}
