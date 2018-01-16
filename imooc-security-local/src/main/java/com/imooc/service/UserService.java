package com.imooc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserService implements UserDetailsService {

    @Autowired
    private IUsersService iUsersService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("获取登录用户信息");
        return new User("steve","$2a$10$456K4Pz.cJyMSGsP8gaTY.nrctvEs/0jb8XgDdIchJtLiVxiidzNK", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        iUsersService.selectOne(
//                new EntityWrapper<>("username",s)
//        );
//        return null;
//    }

}
