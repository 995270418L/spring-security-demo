package com.imooc.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author: steve
 * @Date: Created in 22:31 2018/1/24
 * @Description:  资源服务器配置
 * @Modified By:
 */
@Configuration
@EnableResourceServer
public class ImoocUaaResourceServer extends ResourceServerConfigurerAdapter{


    @Autowired
    private ImoocAuthenticationSuccessfulhandler imoocAuthenticationSuccessfulhandler;



    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/signIn.html")
                .loginProcessingUrl("/login")
                .successHandler(imoocAuthenticationSuccessfulhandler)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                // .maxSessionsPreventsLogin(true) // 是否阻止session并发出现
                // .expiredSessionStrategy(...)   session 过期处理
                .and()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated().and().csrf().disable();
    }
}
