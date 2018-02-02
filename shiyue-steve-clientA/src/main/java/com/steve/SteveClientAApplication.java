package com.steve;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: steve
 * @Date: Created in 13:28 2018/1/30
 * @Description:
 * @Modified By:
 */
@SpringBootApplication
@RestController
public class SteveClientAApplication {

    private Logger logger = LoggerFactory.getLogger(SteveClientAApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SteveClientAApplication.class,args);
    }

    @GetMapping("/user/me")
    public Object getUserInfo(Authentication authentication){
        logger.info("获取用户信息  ------------------->>>>>>>>>>>>>>> {}",authentication);
        return authentication;
    }
}
