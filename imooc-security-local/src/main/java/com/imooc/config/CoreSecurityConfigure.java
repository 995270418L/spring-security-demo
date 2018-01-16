package com.imooc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class CoreSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Autowired
    private ImoocAuthenticationSuccessfulhandler imoocAuthenticationSuccessfulhandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsSecurityConfigure smsSecurityConfigure;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateImageUsernamePasswordFilter filter = new ValidateImageUsernamePasswordFilter();
        filter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);

        ValidateSmsCodeFilter validateSmsCodeFilter = new ValidateSmsCodeFilter();
        validateSmsCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);

        http.formLogin()
                .loginPage("/signIn.html").loginProcessingUrl("/login").successHandler(imoocAuthenticationSuccessfulhandler).failureHandler(imoocAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .rememberMeCookieName("remember-me")
                .userDetailsService(userDetailsService)
                .and()
                .addFilterBefore(validateSmsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/*.html","/me","/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .apply(smsSecurityConfigure);
    }

    /**
     * web 资源的安全配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
