package com.steve.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: steve
 * @Date: Created in 23:05 2018/1/28
 * @Description:
 * @Modified By:
 */
@Configuration
public class PreAuthenticationFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(PreAuthenticationFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        logger.info("请求的Url为: {}", request.getRequestURL());
        if(request.getRequestURI().contains("/uaa")){
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null || cookies.length > 0){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("access_token")){
                    String accessToken = cookie.getValue();
                    if(accessToken != null){
                        context.addZuulRequestHeader("Authorization","Bearer " + accessToken);
                    }
                }
            }
        }else{
            throw new RuntimeException("未授权");
        }
        return null;
    }
}
