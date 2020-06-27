package com.forever.demo.config;

import com.forever.demo.wechat.AppVerification;
import com.forever.demo.wechat.MessageReceiver;
import com.forever.demo.wechat.OAuthHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Jersey extends ResourceConfig {
    public Jersey() {
        // packages("com.forever.demo");
        register(AppVerification.class);
        register(MessageReceiver.class);
        register(OAuthHandler.class);
    }
}
