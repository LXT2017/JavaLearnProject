package com.example.security.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author shawn
 * @version 1.0
 * @ClassName User
 * Description:
 * @date 2023/2/27 20:19
 */

@Data
public class User implements UserDetails {

    private Long id;   // 主键

    private String username;  // 用户名

    private String password;   // 密码

    private String mobile;    // 手机号

    private String roles;    // 用户角色，多个角色之间用逗号隔开

    private boolean enabled;  // 用户是否可用

    // token，选用
    private String token;
    private List<GrantedAuthority> authorities;  // 用户权限集合

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  // 返回用户权限集合
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {  // 账户是否未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  // 账户是否未锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 密码是否未过期
        return true;
    }

    @Override
    public boolean isEnabled() {  // 账户是否可用
        return enabled;
    }

    @Override
    public boolean equals(Object obj) {  // equals() 方法一般要重写
        return obj instanceof User && this.username.equals(((User) obj).username);
    }

    @Override
    public int hashCode() {   // hashCode() 方法一般要重写
        return this.username.hashCode();
    }
}
