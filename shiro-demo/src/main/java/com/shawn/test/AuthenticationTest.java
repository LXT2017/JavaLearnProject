package com.shawn.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
	
	SimpleAccountRealm simpleAccountRealm =  new SimpleAccountRealm();
	
	
	@Before
	public void addUser() {
		simpleAccountRealm.addAccount("admin","123456","admin");
	}
	@Test
	public void testAuthtication() {
		 DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
	      defaultSecurityManager.setRealm(simpleAccountRealm);

	      //2. 主体提交认证请求
	      SecurityUtils.setSecurityManager(defaultSecurityManager);
	      Subject subject = SecurityUtils.getSubject();

	      UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
	        subject.login(token);

	        System.out.println("是否认证通过：" + subject.isAuthenticated());

	        //3. 验证是否拥有指定角色
	        subject.checkRole("admin");

	        //4. 验证用户权限
	        //subject.checkPermissions("user:update");
	}
}
