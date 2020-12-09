package com.example.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot SpringSecurity
 * <p>
 * 这里使用5张表（用户-角色是一对多，角色-权限是多对多），也可以使用3张表进行权限管理
 * <p>
 * 用户表：             tb_user
 * 角色表：             tb_role
 * 用户角色关系表：       tb_user_role
 * 用户权限表：          tb_Permission
 * 用户角色权限关系表：    tb_role_permission
 *
 * @author knox
 */
@SpringBootApplication
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);

        System.out.println("http://localhost:8080/swagger-ui.html");
    }

}
