package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: steve
 * @Date: Created in 15:10 2018/1/26
 * @Description:
 * @Modified By:
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SSOClientAApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOClientAApplication.class,args);
    }

    @GetMapping("/user/me")
    public Object user(Authentication authentication){
        return authentication;
    }
}
