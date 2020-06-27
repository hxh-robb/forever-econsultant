package com.forever.demo.wechat.handler;

import com.forever.demo.wechat.MessageSender;
import com.forever.demo.wechat.msg.Event;
import com.forever.demo.wechat.msg.Resp;
import com.forever.demo.wechat.msg.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Subscribe {
    @Autowired
    private MessageSender sender;

    @Async
    public void handle(Event event){
        Text text = new Text();
        text.setToUserName(event.getFromUserName());
        // text.setContent("欢迎订阅Forever公众号，请先<a href=" + MessageSender.generateOAuthURL("bind") + ">绑定商户信息</a>。");
        text.setContent("欢迎订阅Forever公众号，请先绑定商户信息");
        MessageSender.Task task = new MessageSender.Task(MessageSender.URL_CUSTOM_MESSAGE, text, Resp.class);
        sender.send(task);
    }
}
