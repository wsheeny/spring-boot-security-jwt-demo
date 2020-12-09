package com.example.security.demo.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Data
@Builder
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 15, message = "登录用户名长度在2-15")
    private String username = "admin";

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "登录密码长度在6-20")
    private String password = "123456";

}
