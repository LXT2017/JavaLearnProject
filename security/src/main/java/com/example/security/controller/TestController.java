package com.example.security.controller;

import com.example.security.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shawn
 * @version 1.0
 * @ClassName TestController
 * Description:
 * @date 2023/2/28 19:55
 */
@Controller
public class TestController {
    //...
    @Autowired
    private SessionRegistry sessionRegistry;

    //...
    @GetMapping("/test4")
    @ResponseBody
    public Object getOnlineSession() {
        // 统计当前用户未过期的并发 Session 数量
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SessionInformation> sessions = this.sessionRegistry.getAllSessions(user, false);
        return  Msg.success().add("size",sessions.size());
    }


    @GetMapping("/test5")
    @ResponseBody
    public Object getOnlineUsers() {
        // 统计所有在线用户
        List<String> userList = sessionRegistry.getAllPrincipals().stream()
                .map(user -> ((UserDetails) user).getUsername())
                .collect(Collectors.toList());
        return Msg.success().add("userList",userList);
    }
}
