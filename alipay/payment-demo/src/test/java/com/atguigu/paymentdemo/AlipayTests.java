package com.atguigu.paymentdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class AlipayTests {

    @Resource
    private Environment config;

    @Test
    public void testAlipayConfig(){
        log.info(config.getProperty("alipay.app-id"));
    }
}
