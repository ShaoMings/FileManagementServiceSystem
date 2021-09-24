package com.graduation.exception;

/**
 * Description 文件下载异常
 *
 * @author shaoming
 * @date 2021/8/23 13:48
 * @since 1.0
 */
public class FileDownloadException extends RuntimeException{
    public FileDownloadException(String message){
        super(message);
    }
}
