package com.imooc.utils.validate;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsCodeGenerator implements ValidateCodeGenerator<SmsValidateCode> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final Integer COUNT = 4;  // 短信验证码的长度

    private static final Integer SMSEXPIRETIME = 1800;  // 失效时间： s

    @Override
    public SmsValidateCode generatorValidateCode() {
        String code = RandomStringUtils.randomNumeric(COUNT);
        logger.info("生成短信验证码:{}",code);
        return new SmsValidateCode("",code,SMSEXPIRETIME);
    }
}
