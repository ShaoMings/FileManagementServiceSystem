package com.graduation.handler;

import com.graduation.exception.FileConverterException;
import com.graduation.exception.ResponseException;
import com.graduation.model.vo.FileResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Description 全局异常统一捕获处理  @RestControllerAdvice标注类
 * @ExceptionHandler 给定异常类class 作为捕获的异常对象  标注在处理方法上
 *
 * @author shaoming
 * @date 2021/7/19 15:50
 * @since 1.0
 **/
@Slf4j
@RestControllerAdvice
public class UnifiedExceptionHandler {

//    @ExceptionHandler(value = ResponseException.class)
//    @ResponseBody
//    public String runtimeExceptionHandler(ResponseException e){
//        log.info("捕获到自定义异常!");
//        return e.getMessage();
//    }
//
//    @ExceptionHandler(value = RuntimeException.class)
//    @ResponseBody
//    public String runtimeExceptionHandler(RuntimeException e){
//        log.info("捕获到运行时异常!");
//        return e.getMessage();
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public String exceptionHandler(Exception e){
//        log.info("捕获到编译时异常!");
//        return e.getMessage();
//    }

    @ExceptionHandler(value = FileConverterException.class)
    @ResponseBody
    public FileResponseVo fileConverterExceptionHandler(FileConverterException e){
        return FileResponseVo.fail(e.getMessage());
    }
}
