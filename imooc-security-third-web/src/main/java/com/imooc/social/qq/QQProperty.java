package com.imooc.social.qq;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class QQProperty extends SocialProperties {

    private String providerId;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
