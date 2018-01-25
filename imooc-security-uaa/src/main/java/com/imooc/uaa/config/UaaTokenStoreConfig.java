package com.imooc.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author: steve
 * @Date: Created in 20:23 2018/1/25
 * @Description:
 * @Modified By:
 */
@Configuration
public class UaaTokenStoreConfig{

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "imooc.security.oauth2",name="tokenType",havingValue = "redis")
    public TokenStore getTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Configuration
    public static class JwtTokenStoreConfig{

        @Bean
        public TokenStore getTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        @ConditionalOnProperty(prefix = "imooc.security.oauth2",name="tokenType",havingValue = "jwt", matchIfMissing = true)
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey("steve");
            return jwtAccessTokenConverter;
        }

        @Bean
        public TokenEnhancer jwtTokenEnhancer(){
            return new JwtTokenEnhancer();
        }
    }
}
