package com.graduation.exception;

/**
 * Description 自定义相应异常类
 *
 * @author shaoming
 * @date 2021/7/20 9:29
 * @since 1.0
 **/
public class ResponseException extends RuntimeException {
    public ResponseException(String message) {
        super(message);
    }
}
