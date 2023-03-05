package com.example.security.handler;


import com.example.security.enums.CustomExceptionCode;
import com.example.security.exception.ValidateCodeException;
import com.example.security.pojo.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 登录失败返回给前端消息
 * 继承 SimpleUrlAuthenticationFailureHandler 处理器，该类是 failureUrl() 方法使用的认证失败处理器
 * 也可以直接实现AuthenticationFailureHandler
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 需要在请求的时候加头部信息，否则会认为是表单请求，而不是js请求
    */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String xRequestedWith = request.getHeader("x-requested-with");
        // 判断前端的请求是否为 ajax 请求
        if ("JSON".equals(xRequestedWith)) {
            Msg msg = null;
            if (e instanceof UsernameNotFoundException) {
                msg = Msg.fail(CustomExceptionCode.LOGIN_USER_NOT_EXISTED);
            } else if (e instanceof BadCredentialsException) {
                msg = Msg.fail(CustomExceptionCode.LOGIN_FAILED);
            } else if (e instanceof ValidateCodeException) {
                // 验证码类型错误
                msg = Msg.problem(CustomExceptionCode.LOGIN_VERIFICATION_FAILED.getCode(), e.getMessage());
            } else {
                msg = Msg.fail(CustomExceptionCode.FAILED);
            }
            // 认证失败，响应 JSON 数据
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(msg));
        }else {
            // 以下配置等同于前文的 failureUrl("/login/page?error")

            // 认证失败后，重定向到指定地址
            // 设置默认的重定向路径
            super.setDefaultFailureUrl("/login/page?error");
            // 调用父类的 onAuthenticationFailure() 方法
            super.onAuthenticationFailure(request, response, e);
        }
    }
}