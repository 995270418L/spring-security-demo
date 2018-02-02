package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: steve
 * @Date: Created in 0:55 2018/1/26
 * @Description: JDBC的实现
 * @Modified By:
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * ============================================================================
     * ============================================================================
     * ============================================================================
     *
     *                          JWTokenStore 的配置
     *
     * ============================================================================
     * ============================================================================
     * ============================================================================
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("ClientA").secret("ClientASecret")
                .accessTokenValiditySeconds(7200).scopes("all")
                .authorizedGrantTypes("authorization_code").and()
                .withClient("ClientC").secret("ClientCSecret")
                .accessTokenValiditySeconds(7200).scopes("all")
                .authorizedGrantTypes("authorization_code");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // jwt 独有配置，客户端是否可以获取jwt的签名 key
        security.
                tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // jwtTokenStore的设置
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());

    }

    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("steve");
        return jwtAccessTokenConverter;
    }

    /**
     * ============================================================================
     * ============================================================================
     * ============================================================================
     *
     *                          JDBCTokenStore 的配置
     *
     * ============================================================================
     * ============================================================================
     * ============================================================================
     */
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .tokenStore(tokenStore());
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        defaultTokenServices.setClientDetailsService(jdbcClientDetailsService());
////        defaultTokenServices.setAccessTokenValiditySeconds( 60*60); // 1个小时
//        defaultTokenServices.setSupportRefreshToken(true);
//        endpoints.tokenServices(defaultTokenServices);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(name="jdbcClientDetailsService")
//    public JdbcClientDetailsService jdbcClientDetailsService(){
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(name="tokenStore")
//    public TokenStore tokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }
}
