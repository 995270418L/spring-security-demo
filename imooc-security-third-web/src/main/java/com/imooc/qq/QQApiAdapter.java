package com.imooc.qq;

import com.imooc.entity.UserInfo;
import com.imooc.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQApiAdapter implements ApiAdapter<QQ> {

    /**
     *  测试accessToken是否依然有效， 这里不做实现（facebook有专门的接口实现）
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        UserInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 类似个人主页之类的东西
        values.setProfileUrl(null);
        values.setProviderUserId(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
