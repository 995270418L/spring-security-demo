package com.steve.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author: steve
 * @Date: Created in 13:33 2018/1/30
 * @Description:
 * @Modified By:
 */
@Configuration
@EnableResourceServer
public class ClientResourceConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "steve-client-a";

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                 .stateless(true)
                 .tokenServices(new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),null));
    }
}