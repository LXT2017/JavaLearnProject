package com.shawn.test;
import com.shawn.shiro.realm.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class customerReamlTest {
	
	
	
	
	@Test
	public void testAuthtication() {
		
		CustomRealm customRealm = new CustomRealm();
		 DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
	      defaultSecurityManager.setRealm(customRealm);

	      HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
	      matcher.setHashAlgorithmName("md5");
	      matcher.setHashIterations(1);
	      
	      customRealm.setCredentialsMatcher(matcher);
	      
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
