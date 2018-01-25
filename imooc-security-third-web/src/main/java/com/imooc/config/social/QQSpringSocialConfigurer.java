package com.imooc.config.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author: steve
 * @Date: Created in 14:14 2018/1/22
 * @Description:
 *   处理第三方登陆的访问路径前缀的配置类
 * @Modified By:
 */
public class QQSpringSocialConfigurer extends SpringSocialConfigurer {

    private String prefix;

    public  QQSpringSocialConfigurer(String prefix){
        this.prefix = prefix;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter)super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(prefix);
        socialAuthenticationFilter.setConnectionAddedRedirectUrl("/index");
        return super.postProcess(object);
    }
}
