package com.example.security.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

/**
 * 统一注解，解决前后端交互 Long 类型精度丢失的问题
 */
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 设置日期转换
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置时区
        // objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // 序列化时，值为 null 的属性不序列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为空（"" 或 null）都不序列化
        // Include.NON_NULL 属性为 null 不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 反序列化时，遇到未知属性的时候不抛出异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 序列化成 json 时，将 Long 转换成 String（防止 js 丢失精度）
        // Java 的 Long 能表示的范围比 js 中 number 大，意味着部分数值在 js 会变成不准确的值
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}