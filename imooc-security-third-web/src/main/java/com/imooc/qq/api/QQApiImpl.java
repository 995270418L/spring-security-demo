package com.imooc.qq.api;

import com.imooc.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestTemplate;

public class QQApiImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * appId
     */
    private String oauth_consumer_key;

    /**
     * 用户的id，和QQ号码一一对应 可通过调用https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN 来获取。
     */
    private String openid;

    private static final String GET_USER_OPEN_ID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    // 所有的请求都会把accessToken当参数放入，所以这里去掉accessToken
    private static final String GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    public String getOauth_consumer_key() {
        return oauth_consumer_key;
    }

    public void setOauth_consumer_key(String oauth_consumer_key) {
        this.oauth_consumer_key = oauth_consumer_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public QQApiImpl(String accessToken,String oauth_consumer_key) {

        // 请求的时候会 以参数的形式将accessToken放进请求里，默认是放在header里面，QQ是参数的形式
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        String url = String.format(GET_USER_OPEN_ID,accessToken);
        QQOpenId result = getRestTemplate().getForObject(url,QQOpenId.class);
        if(result != null) {
            this.openid = result.getOpenid();
        }
        this.oauth_consumer_key = oauth_consumer_key;
    }

    @Override
    public UserInfo getUserInfo() {
        String url = String.format(GET_USER_INFO_URL,this.oauth_consumer_key,this.openid);
        return getRestTemplate().getForObject(url,UserInfo.class);
    }

}
