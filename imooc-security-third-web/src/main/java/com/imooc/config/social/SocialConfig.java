package com.imooc.config.social;

import com.imooc.social.SocialProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 配置 JdbcUserConnectionFactory 的配置类
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SocialProperty socialProperty;

    /**
     * 并不是每个使用他的模块都需要这个bean，实习不同，需求也不同。
     */
    @Autowired(required = false)
    private SocialConnectionSignUp socialConnectionSignUp;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
    /**
     * 根据这个connectionFactory 来产生 connection 进而调用
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setTablePrefix("steve_");
        if(socialConnectionSignUp != null) {
            jdbcUsersConnectionRepository.setConnectionSignUp(socialConnectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringSocialConfigurer springSocialConfigurer(){
        String prefix = socialProperty.getPrefix();
        SpringSocialConfigurer springSocialConfigurer = new QQSpringSocialConfigurer(prefix);
        return springSocialConfigurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }

}
