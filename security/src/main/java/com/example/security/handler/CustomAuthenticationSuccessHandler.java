package com.example.security.handler;

import com.alibaba.fastjson2.JSON;
import com.example.security.pojo.Msg;
import com.example.security.pojo.User;
import com.example.security.utils.AccessAddressUtils;
import com.example.security.utils.JwtTokenUtils;
import com.example.security.utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 继承 SavedRequestAwareAuthenticationSuccessHandler 类，该类是 defaultSuccessUrl() 方法使用的认证成功处理器
 * 也可以直接实现AuthenticationSuccessHandler接口类
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String xRequestedWith = request.getHeader("x-requested-with");
        // 判断前端的请求是否为 ajax 请求
        if ("JSON".equals(xRequestedWith)) {

            // 这里可以进行用户信息的操作
            // 认证成功，响应 JSON 数据
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(Msg.success(200,"用户认证成功").add("data",map)));
        }else {
            // 以下配置等同于前文中的 defaultSuccessUrl("/index")

            // 认证成功后，如果存在原始访问路径，则重定向到该路径；如果没有，则重定向 /index
            // 设置默认的重定的路径
            super.setDefaultTargetUrl("/index");
            // 调用父类的 onAuthenticationSuccess() 方法
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}