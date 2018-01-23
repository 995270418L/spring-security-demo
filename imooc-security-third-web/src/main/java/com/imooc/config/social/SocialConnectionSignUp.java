package com.imooc.config.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author: steve
 * @Date: Created in 21:11 2018/1/22
 * @Description:
 * @Modified By:
 */
@Component
public class SocialConnectionSignUp implements ConnectionSignUp {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String execute(Connection<?> connection) {
        // 自定义用户信息插入
        logger.info("第三方用户的连接信息: {}",connection);
        return connection.getDisplayName();
    }
}
