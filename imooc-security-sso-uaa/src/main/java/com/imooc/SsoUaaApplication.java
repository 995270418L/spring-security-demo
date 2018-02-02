package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: steve
 * @Date: Created in 1:06 2018/1/26
 * @Description:
 * @Modified By:
 */
@SpringBootApplication
@RestController
public class SsoUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoUaaApplication.class);
    }

}
