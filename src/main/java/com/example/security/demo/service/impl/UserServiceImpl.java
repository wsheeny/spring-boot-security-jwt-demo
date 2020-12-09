package com.example.security.demo.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.demo.config.jwt.JwtTokenUtil;
import com.example.security.demo.config.redis.RedisService;
import com.example.security.demo.exception.MyException;
import com.example.security.demo.mapper.PermissionMapper;
import com.example.security.demo.mapper.RolePermissionMapper;
import com.example.security.demo.mapper.UserMapper;
import com.example.security.demo.mapper.UserRoleMapper;
import com.example.security.demo.model.dto.LoginDTO;
import com.example.security.demo.model.dto.RegisterDTO;
import com.example.security.demo.model.entity.TbPermission;
import com.example.security.demo.model.entity.TbRolePermission;
import com.example.security.demo.model.entity.TbUser;
import com.example.security.demo.model.entity.TbUserRole;
import com.example.security.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TbUser> implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Qualifier("myUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            if (!userDetails.isEnabled()) {
                throw new MyException().code(HttpServletResponse.SC_UNAUTHORIZED).message("账号已被锁定，请联系管理员处理");
            }
            boolean matches = passwordEncoder.matches(dto.getPassword(), userDetails.getPassword());
            if (!matches) {
                throw new BadCredentialsException("密码错误");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (UsernameNotFoundException e) {
            logger.warn("用户不存在=======>{}", dto.getUsername());
        } catch (BadCredentialsException e) {
            logger.warn("密码验证失败=======>{}", dto.getPassword());
        }
        return token;
    }

    @Override
    public TbUser executeRegister(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        TbUser one = this.baseMapper.selectOne((new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, dto.getName())));
        Assert.isNull(one, "账号已存在，请更换");

        TbUser addUser = TbUser.builder()
                .username(dto.getName())
                .password(passwordEncoder.encode(dto.getPass()))
                .build();
        baseMapper.insert(addUser);

        // 30分钟
        String activeCode = RandomUtil.randomString(8);
        redisService.set("activeCode[" + dto.getName() + "]", activeCode, 30 * 60);
        // 发送邮件
        String activeUrl = URLUtil.normalize("127.0.0.1" + "/#?user=" + dto.getName() + "&code=" + activeCode);
        String content = "请在30分钟内激活您的账号，如非本人操作，请忽略 </br > " +
                "<a href=\"" + activeUrl + "\" target =\"_blank\" '>点击激活账号</a>";
        MailUtil.send(dto.getEmail(), "账号激活", content, true);

        return addUser;
    }

    @Override
    public TbUser getUserByUsername(String username) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, username));
    }

    /**
     * 这里使用5张表，也可以使用3张表进行权限管理
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<TbPermission> getUserPermission(String userId) {

        // 根据用户ID获取用户角色
        List<TbUserRole> tbUserRoles = userRoleMapper.selectList(
                new LambdaQueryWrapper<TbUserRole>().eq(TbUserRole::getUserId, userId));
        List<String> roleIds = tbUserRoles.stream()
                .map(TbUserRole::getRoleId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 根据角色ID获取权限
        List<TbRolePermission> tbRolePermissions = rolePermissionMapper.selectList(
                new LambdaQueryWrapper<TbRolePermission>().in(TbRolePermission::getRoleId, roleIds));
        List<String> permissionIds = tbRolePermissions.stream()
                .map(TbRolePermission::getPermissionId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 获取权限
        return permissionMapper.selectList(
                new LambdaQueryWrapper<TbPermission>().in(TbPermission::getId, permissionIds));
    }
}
