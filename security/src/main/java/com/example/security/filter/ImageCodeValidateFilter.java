package com.example.security.filter;

import com.example.security.exception.ValidateCodeException;
import com.example.security.handler.CustomAuthenticationFailureHandler;
import com.example.security.pojo.CheckCode;
import com.example.security.pojo.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shawn
 * @version 1.0
 * @ClassName ImageCodeValidateFilter
 * Description:
 * @date 2023/2/27 22:16
 */
@Component
public class ImageCodeValidateFilter extends OncePerRequestFilter {

    private String codeParamter = "imageCode";  // 前端输入的图形验证码参数名

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;  // 自定义认证失败处理器

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 非 POST 方式的表单提交请求不校验图形验证码
        if ("/login/form".equals(request.getRequestURI()) && "POST".equals(request.getMethod())) {
            try {
                // 校验图形验证码合法性
                validate(request);
            } catch (ValidateCodeException e) {
                // 手动捕获图形验证码校验过程抛出的异常，将其传给失败处理器进行处理
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        // 放行请求，进入下一个过滤器
        filterChain.doFilter(request, response);
    }

    // 判断验证码的合法性
    private void validate(HttpServletRequest request) {
        // 获取用户传入的图形验证码值
        String requestCode = request.getParameter(this.codeParamter);
        if(requestCode == null) {
            requestCode = "";
        }
        requestCode = requestCode.trim().toLowerCase();

        // 获取 Session
        HttpSession session = request.getSession();
        // 获取存储在 Session 里的验证码值
        CheckCode savedCode = (CheckCode) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (savedCode != null) {
            // 随手清除验证码，无论是失败，还是成功。客户端应在登录失败时刷新验证码
            session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        }

        // 校验出错，抛出异常
        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (savedCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (savedCode.isExpried()) {
            throw new ValidateCodeException("验证码过期");
        }

        if (!requestCode.equalsIgnoreCase(savedCode.getCode())) {
            throw new ValidateCodeException("验证码输入错误");
        }
    }
}
