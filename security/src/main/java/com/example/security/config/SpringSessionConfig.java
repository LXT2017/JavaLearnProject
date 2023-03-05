package com.example.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author 86180
 */
@Configuration
public class SpringSessionConfig {
    @Bean
    public CookieSerializer httpSessionIdResolver() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // 取消仅限同一站点设置,防止跨域造成的session不一样，这样验证码就会有问题
        cookieSerializer.setSameSite(null);
        return cookieSerializer;
    }
}

