package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author shawn
 * @version 1.0
 * @ClassName LoginController
 * Description:
 * @date 2023/2/27 19:39
 */
@Controller
public class LoginController {

    @GetMapping("/login/page")
    public String loginPage() {  // 获取登录页面
        return "login";
    }

}
