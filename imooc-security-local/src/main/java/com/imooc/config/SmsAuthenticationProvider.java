package com.imooc.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = (String) smsCodeAuthenticationToken.getPrincipal();
        Assert.hasText(mobile,"电话号码不能为空");
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

        if(userDetails == null){
            throw new InternalAuthenticationServiceException("用户不存在");
        }

        // 重新构建authentication对象
        SmsCodeAuthenticationToken smsCodeAuthenticationToken1 = new SmsCodeAuthenticationToken(mobile,userDetails.getAuthorities());

        // Token中放入已经认证的用户信息，接下来SecurityContextHolder会从session中将用户信息读取并存入本地线程变量
        smsCodeAuthenticationToken1.setDetails(smsCodeAuthenticationToken.getDetails());

        return smsCodeAuthenticationToken;
    }

    /**
     *  判断authentication是否是SmsCodeAuthenticationToken类型的，是就用这个 SmsAuthenticationProvider 处理登陆
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
