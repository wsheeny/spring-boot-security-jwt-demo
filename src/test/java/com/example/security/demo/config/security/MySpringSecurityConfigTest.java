package com.example.security.demo.config.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MySpringSecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testPass() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}