package com.example.security.handler;

/**
 * Author: CCY
 * Description:
 */


import com.example.security.enums.CustomExceptionCode;
import com.example.security.pojo.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义未认证访问处理器
 * 也可以直接实现AuthenticationEntryPoint
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    ObjectMapper objectMapper;


    /**
     * 未登录时返回给前端数据,注意这是json数据返回了
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Msg result = Msg.fail(CustomExceptionCode.LOGIN_NEED);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}