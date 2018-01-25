package com.imooc.config;

import com.imooc.utils.ValidateException;
import com.imooc.utils.validate.ImageValidateCode;
import com.imooc.utils.validate.SmsValidateCode;
import com.imooc.utils.validate.ValidateCode;
import com.imooc.utils.validate.processor.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class ValidateSmsCodeFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 判断是否来自登陆请求
        if(request.getRequestURL().toString().endsWith("/authentication/mobile") && request.getMethod().equals("POST")){
            try{
                validate(request);
            }catch (ValidateException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException, ValidateException {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        ValidateCode smsValidateCode = (ValidateCode) sessionStrategy.getAttribute(new ServletWebRequest(request), ValidateCodeProcessor.VALIDATE_SESSION_KEY);
        String formCode = ServletRequestUtils.getStringParameter(request,"smsCode");
        if(StringUtils.isEmpty(formCode)){
            throw new ValidateException("验证码不能为空");
        }
        if(smsValidateCode.getCode() == null ){
            throw new ValidateException("验证码不存在！！");
        }
        // 判断是否超过了过期时间
        if(smsValidateCode.getExpireTime().compareTo(LocalDateTime.now()) < 0){
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeProcessor.VALIDATE_SESSION_KEY);
            throw new ValidateException("验证码过期，请刷新重试");
        }
        if(!formCode.equals(smsValidateCode.getCode())){
            throw new ValidateException("验证码错误");
        }
        // 通过之后删除session
        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeProcessor.VALIDATE_SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
