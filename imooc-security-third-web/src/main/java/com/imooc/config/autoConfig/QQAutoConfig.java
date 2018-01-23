package com.imooc.config.autoConfig;


import com.imooc.social.SocialProperty;
import com.imooc.social.qq.QQConnectionFactory;
import com.imooc.social.qq.QQProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "spring.social.QQ", name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SocialProperty socialProperty;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperty qqProperty = socialProperty.getQq();
        return new QQConnectionFactory(qqProperty.getProviderId(), qqProperty.getAppId(), qqProperty.getAppSecret());
    }
}
