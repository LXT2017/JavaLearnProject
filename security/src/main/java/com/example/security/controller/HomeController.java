package com.example.security.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author shawn
 * @version 1.0
 * @ClassName HomeController
 * Description:
 * @date 2023/2/27 19:38
 */
@Controller
public class HomeController {


    @GetMapping({"/", "/index"})
    @ResponseBody
    public String index() {   // 跳转到主页
        return "欢迎您登录！！！";
    }


    /**
     * 该方法仅仅只有admin可以访问，user访问会返回403
     * 如果不希望匹配这个前缀，那么改为调用 hasAuthority() 方法即可
     * 相当于这个前缀都是需要这个权限的人才能访问
    */
    @GetMapping("/admin/hello")
    @ResponseBody
    public String admin() {   // 跳转到主页
        return "欢迎admin登录！！！";
    }

    @GetMapping({"/user/hello"})
    @ResponseBody
    public String user() {   // 跳转到主页
        return "欢迎user登录！！！";
    }


    /**
     * 由前文可知，封装了已认证用户信息对象 Authentication 的 SecurityContext 即存储在 SecurityContextHolder 中，
     * 也存储在 Session 中，所以可以有两种方式获取用户信息。
    */
    @GetMapping("/test1")
    @ResponseBody
    public Object test1() {

        // 从 SecurityContextHolder 获取认证用户信息对象 Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 从 Authentication 中获取 UserDetails
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return user;
    }

    @GetMapping("/test2")
    @ResponseBody
    public Object test3(HttpSession session) {
        // 获取 Session 获取 SecurityContext
        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        // 从 Authentication 中获取 UserDetails
        UserDetails user = (UserDetails) context.getAuthentication().getPrincipal();
        return user;
    }

}
