package com.imooc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: steve
 * @Date: Created in 22:32 2018/1/24
 * @Description:
 * @Modified By:
 */
@SpringBootApplication
@RestController
public class BaseApplication {
    private static final Logger logger = LoggerFactory.getLogger(BaseApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication sp = new SpringApplication(BaseApplication.class);
        display(sp.run(args).getEnvironment());
    }

    private static void display(Environment environment) throws UnknownHostException {
        logger.info("\n-----------------------------------------------\nImooc Application: {} is Running;\nLocalUrl is : {}",new Object[]{environment.getProperty("spring.application.name"), InetAddress.getLocalHost().getHostAddress()+":"+environment.getProperty("server.port")});
    }

    @GetMapping("/index")
    public String index(){
        return "hello";
    }

    @GetMapping("/user/me")
    public Authentication useInfo(Authentication authentication, HttpServletRequest request) throws Exception {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(authorization,"bearer ");
        Claims claims = Jwts.parser().setSigningKey("steve".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        logger.info("addtionalInfo: {}",claims.get("company"));
        return authentication;
    }
}
