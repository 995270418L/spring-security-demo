package com.imooc.qq;


import com.imooc.qq.api.QQ;
import com.imooc.qq.api.QQApiImpl;
import org.springframework.social.ServiceProvider;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 这个类才是实现OAuth2标准授权流程的springSocial包装类
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

    /**
     * 获取授权码的地址
     */
    private static final String AUTHORIZED_URL = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 获取accessToken的地址
     */
    private static final String ACCESSTOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    /**
     * 实例化这个类就会实现前5步获取 accessToken
     * @param clientId
     * @param clientSecret
     */
    public QQServiceProvider(String clientId,String clientSecret) {
        super(new OAuth2Template(clientId,clientSecret,AUTHORIZED_URL,ACCESSTOKEN_URL));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQApiImpl(accessToken,appId);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
