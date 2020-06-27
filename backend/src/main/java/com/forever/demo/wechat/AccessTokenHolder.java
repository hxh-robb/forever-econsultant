package com.forever.demo.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenHolder {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenHolder.class);

    private static String accessToken;
    public static String getAccessToken(){
        return accessToken;
    }

    private boolean menuUpdated = false;

    @Value("${wechat.app.id}")
    private String id;

    @Value("${wechat.app.secret}")
    private String secret;

    @Autowired
    private MessageSender sender;

    @Scheduled(initialDelay = 0, fixedRate = 3600000)
    public synchronized void updateAccessToken(){
        accessToken = sender.getAccessToken(id, secret);

        if(null != accessToken && !menuUpdated){
            menuUpdated = sender.createMenu(accessToken);
        }
    }
}
