package com.share.web.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {



    @ExceptionHandler
    public String baseException(Exception e) {
        log.error(e.getMessage(), e);
        return "自定义异常返回:"+e.getMessage();
    }

}
