package com.example.security.handler;


import com.example.security.enums.CustomExceptionCode;
import com.example.security.exception.ValidateCodeException;
import com.example.security.pojo.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败后返回的类
 * 也可以直接实现AccessDeniedHandler
 */
@Component
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {
    @Autowired
    ObjectMapper objectMapper;

    /**
     * 需要在请求的时候加头部信息，否则会认为是表单请求，而不是js请求
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        String xRequestedWith = request.getHeader("x-requested-with");
        // 判断前端的请求是否为 ajax 请求
        if ("JSON".equals(xRequestedWith)) {
            Msg result = Msg.fail(CustomExceptionCode.LOGIN_NO_ACCESS);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }else {
            super.handle(request,response,e);
        }
    }
}
