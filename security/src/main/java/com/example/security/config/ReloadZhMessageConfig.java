package com.example.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 加载中文认证信息提示配置
 * 可以不管
 */
@Configuration
public class ReloadZhMessageConfig {
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        // 加载中文的认证提示信息
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 不需要添加 .properties 后缀
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        return messageSource;
    }
}
