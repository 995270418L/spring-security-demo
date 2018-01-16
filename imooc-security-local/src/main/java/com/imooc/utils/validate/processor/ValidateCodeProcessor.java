package com.imooc.utils.validate.processor;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 验证码发送接口: 封装了具体的发送流程。
 *  1: 创建验证码，
 *  2: 保存session，
 *  3: 发送
 */
public interface ValidateCodeProcessor {

    String VALIDATE_SESSION_KEY = "validate_code_name";

    void createValidateProcessor(ServletWebRequest request) throws IOException;
}
