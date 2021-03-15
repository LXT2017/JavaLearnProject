package com.shawn.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shawn.dao.User;

@Controller
public class userController {
	
	@RequestMapping(value = "/subLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String subLogin(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		
		try {
			token.setRememberMe(user.getRememberMe());
			subject.login(token);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
		if(subject.hasRole("user")) {
			return "有admin权限";
		}
		return "登陆成功";
	}

	@RequiresRoles("user")
	@RequestMapping(value="/testRole", method=RequestMethod.GET)
	@ResponseBody
	public String testRole() {
		return "test role success";
	}
	
	@RequestMapping(value="/testRoles", method=RequestMethod.GET)
	@ResponseBody
	public String testRoles() {
		return "test roles success";
	}
	
	@RequestMapping(value="/testRoles1", method=RequestMethod.GET)
	@ResponseBody
	public String testRoles1() {
		return "test roles1 success";
	}
}
