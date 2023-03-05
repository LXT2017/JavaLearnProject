package com.example.security.handler;

import com.example.security.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 自定义认证器
 * 验证逻辑，比较传入的 pwd 和 从数据库中拿到的 pwd。
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserDetailsServiceImpl userDetailsService;

    /**
     *  这里有个循环依赖问题，在配置文件改成spring.main.allow-circular-references: true 即可
     *  或者将PasswordEncoder这个bean类单独生成一个文件
     */
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param authentication 验证器
     * @return 验证器
     * @throws AuthenticationException .
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 获取用户名
        String account = authentication.getName();
        // 获取密码
        String password = (String) authentication.getCredentials();
        // 记录login请求日志
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        UserDetails userDetails = userDetailsService.loadUserByUsername(account);
        boolean checkPassword = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
        if (!checkPassword) {
            throw new BadCredentialsException("密码不正确，请重新登录!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
