package com.shawn.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 〈〉
 *
 * @author wolf
 * @create 2018/6/19
 * @since 1.0.0
 */
public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    {
    	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void testAuthentication() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);//开启权限验证

        //自定义验证sql
        String sql = "select password from users where username = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        //自定义角色查询
        String roleSql = "select role_name from user_role where user_name = ?";
        jdbcRealm.setUserRolesQuery(roleSql);
       //自定义权限认证

        String permissionSql = "select permission from roles_permissions where role_name = ?";
        jdbcRealm.setPermissionsQuery(permissionSql);

        //1. 构建SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2. 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);

        System.out.println("是否认证通过：" + subject.isAuthenticated());

        //3. 验证是否拥有指定角色
        subject.checkRole("user");

        //4. 验证用户权限
        subject.checkPermissions("user:add");
    }
}
