package com.imooc.utils.validate;

public class SmsValidateCode extends ValidateCode{

    /**
     * 短信服务商的地址
     */
    private String url;

    public SmsValidateCode(String url, String code, Integer expireTime){
        super(code,expireTime);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
