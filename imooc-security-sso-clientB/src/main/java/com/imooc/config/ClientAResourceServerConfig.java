package com.imooc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author: steve
 * @Date: Created in 14:01 2018/1/27
 * @Description:
 * @Modified By:
 */
//@Configuration
//@EnableResourceServer
public class ClientAResourceServerConfig extends ResourceServerConfigurerAdapter {

    private String RESOURCE_ID = "ClientB";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .requestMatchers().anyRequest().and()
                .anonymous().and()
                .authorizeRequests().antMatchers("/user/**").authenticated();  // 配置order的访问配置，必须认证过才能访问
    }

}
