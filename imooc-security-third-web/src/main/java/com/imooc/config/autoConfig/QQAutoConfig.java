package com.imooc.config.autoConfig;

import com.imooc.qq.QQConnectionFactory;
import com.imooc.qq.QQProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "spring.social.QQ", name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private QQProperty qqProperty;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory("qq",qqProperty.getAppId(),qqProperty.getAppSecret());
    }
}
