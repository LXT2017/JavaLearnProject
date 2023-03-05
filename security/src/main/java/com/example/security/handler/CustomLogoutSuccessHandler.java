package com.example.security.handler;


import com.example.security.pojo.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 继承 SimpleUrlLogoutSuccessHandler 处理器，该类是 logoutSuccessUrl() 方法使用的成功注销登录处理器
 * 也可以直接实现LogoutSuccessHandler
 */
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String xRequestedWith = request.getHeader("x-requested-with");
        // 判断前端的请求是否为 ajax 请求
        if ("JSON".equals(xRequestedWith)) {
            // 成功注销登录，响应 JSON 数据
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(Msg.success(200, "注销登录成功！")));
        }else {
            // 以下配置等同于在 http.logout() 后配置 logoutSuccessUrl("/login/page?logout")

            // 设置默认的重定向路径
            super.setDefaultTargetUrl("/login/page?logout");
            // 调用父类的 onLogoutSuccess() 方法
            super.onLogoutSuccess(request, response, authentication);
        }
    }
}