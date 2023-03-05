package com.example.security.handler;


import com.alibaba.fastjson2.JSON;
import com.example.security.enums.CustomExceptionCode;
import com.example.security.pojo.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = AuthenticationException.class)
    public void authenticationException(AuthenticationException e){
        throw e;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public void accessDeniedException(AccessDeniedException e){
        throw e;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Msg ex(Exception e){
        log.error("异常统一处理:",e);
        return Msg.fail(CustomExceptionCode.SYSTEM_EXCEPTION);
    }
}


