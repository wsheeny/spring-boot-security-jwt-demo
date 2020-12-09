package com.example.security.demo.config.security;

import com.example.security.demo.model.entity.TbPermission;
import com.example.security.demo.model.entity.TbUser;
import com.example.security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现；
 *
 * @author Knox
 * @date 2020/12/6
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取登录用户信息
        TbUser user = userService.getUserByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {
            List<TbPermission> permissionList = userService.getUserPermission(user.getId());
            return new MyUserDetails(user, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
