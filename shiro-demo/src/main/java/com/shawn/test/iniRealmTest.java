package com.shawn.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;

import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class iniRealmTest {
	
	 IniRealm iniRealm = new IniRealm("classpath:shiro.ini");



	  @Test
	  public void testAuthentication() {
		  DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
	      defaultSecurityManager.setRealm(iniRealm);

	      //2. 主体提交认证请求
	      SecurityUtils.setSecurityManager(defaultSecurityManager);
	      Subject subject = SecurityUtils.getSubject();

	      UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
	        subject.login(token);

	        System.out.println("是否认证通过：" + subject.isAuthenticated());

	        //3. 验证是否拥有指定角色
	        subject.checkRole("admin");

	        //4. 验证用户权限
	        subject.checkPermissions("user:delete");
	        
	  }
}
