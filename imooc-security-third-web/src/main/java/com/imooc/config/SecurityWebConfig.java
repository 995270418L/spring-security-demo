package com.imooc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImoocAuthenticationSuccessfulhandler imoocAuthenticationSuccessfulhandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public SpringSocialConfigurer springSocialConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/sign-in.html").successHandler(imoocAuthenticationSuccessfulhandler)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .authorizeRequests()
                .antMatchers("/login/**","/sign-in.html").permitAll()
                .anyRequest().authenticated();
    }
}
