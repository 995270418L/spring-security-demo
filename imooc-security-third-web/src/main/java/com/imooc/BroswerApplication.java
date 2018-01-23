package com.imooc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.UsersConnectionRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class BroswerApplication{

    private static final Logger logger = LoggerFactory.getLogger(BroswerApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication sp = new SpringApplication(BroswerApplication.class);
        display(sp.run(args).getEnvironment());
    }

    private static void display(Environment environment) throws UnknownHostException {
        logger.info("\n-----------------------------------------------\nImooc Application: {} is Running;\tLocalUrl is : {}",new Object[]{environment.getProperty("spring.application.name"), InetAddress.getLocalHost().getHostAddress()+":"+environment.getProperty("server.port")});
    }

}
