package com.imooc.utils.validate.processor;

import com.imooc.utils.validate.SmsValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class SmsCodeProcessor extends ValidateCodeAbstractProcessor<SmsValidateCode> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 发送验证码的具体操作
     * @param request
     * @param code
     */
    @Override
    public void validateProcessor(ServletWebRequest request, SmsValidateCode code) {
        logger.info("正在向url: {} 发送验证码: {}", new Object[]{code.getUrl(),code.getCode()});
    }
}
