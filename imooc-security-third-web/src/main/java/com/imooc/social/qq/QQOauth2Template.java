package com.imooc.social.qq;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.security.provider.OAuth1AuthenticationService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Author: steve
 * @Date: Created in 18:53 2018/1/22
 * @Description:
 * @Modified By:
 */
public class QQOauth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QQOauth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        super.setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate =  super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        // result: access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        String result = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        logger.info("response text: " + result);
        String[] values = result.split("&");
        String accessToken = StringUtils.substringAfter(values[0],"=");
        Long expiresIn = new Long(StringUtils.substringAfter(values[1],"="));
        System.out.println("过期时间: " + expiresIn);
        String refreshToken = StringUtils.substringAfter(values[2],"=");
        return new AccessGrant(accessToken,null,refreshToken,expiresIn);
    }
}
