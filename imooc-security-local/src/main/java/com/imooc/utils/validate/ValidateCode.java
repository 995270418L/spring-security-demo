package com.imooc.utils.validate;

import java.time.LocalDateTime;

/**
 *  验证码父类，包括邮箱验证码，短信验证码，图形验证码。
 */
public class ValidateCode {

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
