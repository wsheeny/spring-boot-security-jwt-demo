package com.example.security.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.security.demo.common.R;
import com.example.security.demo.exception.MyException;
import com.example.security.demo.model.dto.LoginDTO;
import com.example.security.demo.model.dto.RegisterDTO;
import com.example.security.demo.model.entity.TbUser;
import com.example.security.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/user")
@Api(tags = "用户控制器", value = "UserController")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public R userLogin(@Valid @RequestBody LoginDTO dto) {
        String token = userService.executeLogin(dto);
        if (ObjectUtil.isEmpty(token)) {
            throw new MyException(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误");
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return R.ok().data(map).message("登录成功");
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public R register(@Valid @RequestBody RegisterDTO dto) {
        TbUser user = userService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return R.error().message("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return R.ok().data(map).message("账号注册成功");
    }

    /**
     * 注销，Token
     */
    @PostMapping("/logout")
    @ApiOperation(value = "注销")
    public R logout() {
        SecurityContextHolder.clearContext();
        return R.ok().message("注销成功");
    }
}
