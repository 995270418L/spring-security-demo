package com.imooc.social;

import com.imooc.social.qq.QQProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: steve
 * @Date: Created in 9:58 2018/1/22
 * @Description: 社交的配置读取类
 * @Modified By:
 */
@ConfigurationProperties(prefix = "spring.social")
@Configuration
public class SocialProperty {

    /**
     * 默认的前缀
     */
    private String prefix;

    private QQProperty qq;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public QQProperty getQq() {
        return qq;
    }

    public void setQq(QQProperty qq) {
        this.qq = qq;
    }
}
