package com.graduation.exception;

/**
 * Description 文件转换错误异常
 *
 * @author shaoming
 * @date 2021/8/20 13:09
 * @since 1.0
 */
public class FileConverterException extends RuntimeException {
    public FileConverterException(String msg){
        super(msg);
    }
}
