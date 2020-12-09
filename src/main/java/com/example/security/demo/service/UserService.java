package com.example.security.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.demo.model.dto.LoginDTO;
import com.example.security.demo.model.dto.RegisterDTO;
import com.example.security.demo.model.entity.TbPermission;
import com.example.security.demo.model.entity.TbUser;

import java.util.List;

/**
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
public interface UserService extends IService<TbUser> {

    /**
     * 注册账号
     *
     * @param dto 注册参数
     * @return 注册成功信息
     */
    TbUser executeRegister(RegisterDTO dto);

    /**
     * 执行登录
     *
     * @param dto 登录参数
     * @return 登录token
     */
    String executeLogin(LoginDTO dto);

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    TbUser getUserByUsername(String username);

    /**
     * 获取用户权限
     *
     * @param id 用户ID
     * @return 权限集合
     */
    List<TbPermission> getUserPermission(String id);

}
