package com.steve.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: steve
 * @Date: Created in 23:35 2018/1/28
 * @Description:
 * @Modified By:
 */
@Configuration
public class UaaUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UaaUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("正在登陆用户名: {}",username);
        return new User(username,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
