package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: steve
 * @Date: Created in 15:11 2018/1/26
 * @Description:
 * @Modified By:
 */
@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SSOClientBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOClientBApplication.class,args);
    }

    @GetMapping("/user/me")
    public Object user(Authentication authentication){
        return authentication;
    }
}
