package com.imooc.qq;

import com.imooc.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId,String clientId, String clientSecret) {
        super(providerId,new QQServiceProvider(clientId,clientSecret),new QQApiAdapter());
    }

}
