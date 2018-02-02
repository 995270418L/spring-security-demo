package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: steve
 * @Date: Created in 19:06 2018/1/28
 * @Description:
 * @Modified By:
 */
@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SSOClientCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOClientCApplication.class,args);
    }

    @GetMapping("/user/me")
    public Object getUserInfo(OAuth2Authentication authentication){
        return authentication;
    }

}