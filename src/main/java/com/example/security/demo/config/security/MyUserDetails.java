package com.example.security.demo.config.security;

import com.example.security.demo.model.entity.TbPermission;
import com.example.security.demo.model.entity.TbUser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限），需要自行实现；
 *
 * @author Knox
 * @date 2020/12/5
 */
@Slf4j
@Data
public class MyUserDetails implements UserDetails {

    private final TbUser user;

    private final List<TbPermission> permissionList;

    // 登录信息存储
    public MyUserDetails(TbUser user, List<TbPermission> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的权限
        List<SimpleGrantedAuthority> collect = permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
        log.info("user permissions:{}", collect);
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
