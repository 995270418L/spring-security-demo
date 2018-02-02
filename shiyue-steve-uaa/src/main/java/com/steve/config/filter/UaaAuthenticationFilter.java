package com.steve.config.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @Author: steve
 * @Date: Created in 16:23 2018/1/31
 * @Description:
 * @Modified By:
 */
@Configuration
public class UaaAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private ResourceServerTokenServices tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String accessToken = null;
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("access_token")){
                     accessToken = cookie.getValue();
                }
            }
        }
        if(accessToken != null) {
            OAuth2Authentication authentication = tokenService.loadAuthentication(accessToken);
            try {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } finally {
                SecurityContextHolder.clearContext();
            }
        }else{
            SecurityContextHolder.clearContext();
            return;
        }
    }
}
