package com.steve.api.v1;

import com.steve.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author: steve
 * @Date: Created in 23:39 2018/1/28
 * @Description:
 * @Modified By:
 */
@RestController
public class UaaLoginController {

    private Logger logger = LoggerFactory.getLogger(UaaLoginController.class);

    private static final Field[] USERINFO_FIELDS_CACHE = UserDetails.class
            .getDeclaredFields();

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/oauth/login")
    public Object loginRequest(String username, String password, HttpServletRequest request,HttpServletResponse response) throws HttpRequestMethodNotSupportedException {
        Map<String,String> oauthParam = new HashMap();
        oauthParam.put("username", username);
        oauthParam.put("password", password);
        oauthParam.put("grant_type", "password");
        oauthParam.put("scope", "all");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("app","", Collections.emptyList());
        ResponseEntity<OAuth2AccessToken> responseEntity =  tokenEndpoint.postAccessToken(usernamePasswordAuthenticationToken,oauthParam);
        String accessToken = responseEntity.getBody().toString();
        Cookie cookie = new Cookie("access_token",accessToken);
        response.addCookie(cookie);
        return accessToken;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = {
            "/oauth/me/{service}", "/oauth/user/{service}",
            "/oauth/userinfo/{service}", "/oauth/user", "/oauth/userinfo",
            "/oauth/me"})
    public Map<String, Object> me(
            @PathVariable(required = false) String service,
            OAuth2Authentication auth) {
        Map<String, Object> me = Collections.EMPTY_MAP;

//        List<SimpleGrantedAuthorityImpl> authorities = new ArrayList<SimpleGrantedAuthorityImpl>();

        logger.info(
                "/oauth/me request... OAuth2Authentication[{}] service[{}]",
                auth, service);
        // 认证失败
        if (auth == null) {
            throw new OAuth2Exception("Authorization message is not null.");
        }

        OAuth2Request request = auth.getOAuth2Request();
        if (request == null) {
            me.clear();
            throw new OAuth2Exception("OAuth2Request message is not null.");
        }

        Authentication authentication = auth.getUserAuthentication();

        // 登录用户授权模式 password,authorization_code,refresh_token
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
            UserDetails userInfo = new User(usernamePasswordAuthenticationToken.getName(),"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
            // 将密码清空
//            userInfo.setAuthorities(authorities);
            me.put("name", userInfo.getUsername());
            for (Field field : USERINFO_FIELDS_CACHE) {
                Method m = ReflectionUtils.findMethod(userInfo.getClass(),
                        SecurityUtils.getMethodName(field.getName()));
                if (null != m) {
                    me.put(field.getName(),
                            ReflectionUtils.invokeMethod(m, userInfo));
                }
            }
            return me;
        }

        me.clear();
        throw new OAuth2Exception("Bad request.");
    }

    @GetMapping("/oauth/info")
    public Object getLoginInfo(OAuth2Authentication authentication){
        return authentication;
    }

}
