package com.shawn.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.shawn.dao.User;
import com.shawn.dao.UserDao;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/6/24 0024.
 */


public class CustomRealm extends AuthorizingRealm{
	@Resource
   private UserDao userDao;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        
        // 从数据库中根据用户名获取角色数据
        Set<String> roles = getRolesByUserName(username);
        // 从数据库中根据用户名获取权限数据
        System.out.println(roles);
        Set<String> permissions = getPermissionbyUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 模拟用户权限信息
     * @param username
     * @return
     */
    private Set<String> getPermissionbyUsername(String username) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    /**
     * 模拟根据用户名获取数据库中的角色数据
     * @param username
     * @return
     */
	private Set<String> getRolesByUserName(String username) {
		System.out.println("从数据库中获取数据");
		List<String> list = userDao.getRolesByUserName(username);
		Set<String> sets = new HashSet<String>(list);
		return sets;
	}

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.从主体传过来的认证信息中，获取用户名
        String username = (String) authenticationToken.getPrincipal();
        System.out.println("2"+username);
        // 2.通过用户名去到数据库中获取凭证
        String password = getPasswordByUsername(username);
        if(password == null) {
            return null;
        }
        System.out.println(password);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "costomRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));//设置盐salt
        
        return authenticationInfo;
    }

    /**
     * 模拟数据库查询凭证
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {
    	User user = userDao.getUserByUserName(username);
    	if(user == null) {
    		return null;
    	}
        return user.getPassword();
    }
    
    public static void main(String[] args) {
        // 加salt密码
    	
        Md5Hash md5Hash = new Md5Hash("123456", "admin");
        System.out.println(md5Hash);
    }
}