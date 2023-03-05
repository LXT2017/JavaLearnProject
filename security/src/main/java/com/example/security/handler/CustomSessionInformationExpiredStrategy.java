package com.example.security.handler;

/**
 * @author shawn
 * @version 1.0
 * @ClassName CustomSessionInformationExpiredStrategy
 * Description:
 * @date 2023/2/28 19:52
 */

import com.example.security.pojo.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前提：Session 并发处理的配置为 maxSessionsPreventsLogin(false)
 * 用户的并发 Session 会话数量达到上限，新会话登录后，最老会话会在下一次请求中失效，并执行此策略
 */
@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    private ObjectMapper objectMapper;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        HttpServletRequest request = event.getRequest();
        HttpServletResponse response = event.getResponse();

        // 最老会话被踢下线时显示的信息
        UserDetails userDetails = (UserDetails) event.getSessionInformation().getPrincipal();
        String msg = String.format("用户[%s]在另外一台机器登录，您已下线！", userDetails.getUsername());

        String xRequestedWith = event.getRequest().getHeader("x-requested-with");
        // 判断前端的请求是否为 ajax 请求
        if ("JSON".equals(xRequestedWith)) {
            // 认证成功，响应 JSON 数据
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(Msg.problem(400,msg)));
        }else {
            // 返回到登录页面显示信息
            AuthenticationException e = new AuthenticationServiceException(msg);
            request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", e);
            redirectStrategy.sendRedirect(request, response, "/login/page?error");
        }
    }
}