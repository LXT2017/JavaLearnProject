package com.example.security.exception;

/**
 * @author shawn
 * @version 1.0
 * @ClassName ValidateCodeException
 * Description:
 * @date 2023/2/27 22:15
 */

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码校验错误的异常类，继承 AuthenticationException
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
