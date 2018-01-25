package com.imooc.utils.validate.processor;

import com.imooc.utils.validate.ImageValidateCode;
import com.imooc.utils.validate.ValidateCode;
import com.imooc.utils.validate.ValidateCodeGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public abstract class ValidateCodeAbstractProcessor<T extends ValidateCode> implements ValidateCodeProcessor{

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String,ValidateCodeGenerator<T>> validateCodeGenerator;

    @Override
    public void createValidateProcessor(ServletWebRequest request) throws IOException {
        T code = creatValidateCode(request);
        saveValidateSession(request,code);
        validateProcessor(request,code);
    }

    /**
     * 生成验证码
     * @param request
     * @return
     */
    public T creatValidateCode(ServletWebRequest request){
        String type  = getProcessType(request);
        return validateCodeGenerator.get(type + "CodeGenerator").generatorValidateCode();
    }

    private String getProcessType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI().toString(),"/code/");
    }

    public void saveValidateSession(ServletWebRequest request,T code){
        ValidateCode newCode = new ValidateCode(code.getCode(),code.getExpireTime());
        sessionStrategy.setAttribute(request, VALIDATE_SESSION_KEY, newCode);
    }

    public abstract void validateProcessor(ServletWebRequest request,T code) throws IOException;

}
