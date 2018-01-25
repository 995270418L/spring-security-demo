package com.imooc;


import com.imooc.utils.validate.processor.ValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@SpringBootApplication
@RestController
//@EnableResourceServer
public class CoreApplication implements CommandLineRunner{

    private static final Logger logger = LoggerFactory.getLogger(CoreApplication.class);

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication sp = new SpringApplication(CoreApplication.class);
        display(sp.run(args).getEnvironment());
    }

    private static void display(Environment environment) throws UnknownHostException {
        logger.info("\n-----------------------------------------------\nImooc Application: {} is Running;\tLocalUrl is : {}",new Object[]{environment.getProperty("spring.application.name"), InetAddress.getLocalHost().getHostAddress()+":"+environment.getProperty("server.port")});
    }

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessor;

    @GetMapping("/hello")
    public String hello(){
        return "hello java springsecurity";
    }

    @GetMapping("/me")
    public Object getCurrentUserInfo(Authentication authentication){
        return authentication;
    }

    @GetMapping("/userDetails")
    public Object getUserDetails(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @GetMapping("/code/{type}")
    public void generatorValidateCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException {
        validateCodeProcessor.get(type + "CodeProcessor").createValidateProcessor(new ServletWebRequest(request,response));
    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
