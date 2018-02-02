package com.steve.config.jwt;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * @Author: steve
 * @Date: Created in 20:56 2018/1/29
 * @Description:
 * @Modified By:
 */
public class UaaTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        return accessToken;
    }
}
