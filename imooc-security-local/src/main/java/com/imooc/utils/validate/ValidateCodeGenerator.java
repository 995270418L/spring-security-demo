package com.imooc.utils.validate;

/**
 * 验证码生成器
 */
public interface ValidateCodeGenerator<T> {

    T generatorValidateCode();
}
