package com.imooc.uaa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: steve
 * @date: Created in 22:06 2018/1/25
 * @desciption: 给jwt生成的token 加点东西
 * @modified By:
 */
public class JwtTokenEnhancer implements TokenEnhancer{

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken)accessToken;
        Map<String, Object> param = new HashMap();
        param.put("company","steve");
        defaultOAuth2AccessToken.setAdditionalInformation(param);
        return accessToken;
    }
}
