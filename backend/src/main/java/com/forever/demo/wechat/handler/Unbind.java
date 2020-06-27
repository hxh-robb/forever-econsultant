package com.forever.demo.wechat.handler;

import com.forever.demo.wechat.MessageSender;
import com.forever.demo.wechat.msg.Event;
import com.forever.demo.wechat.msg.Resp;
import com.forever.demo.wechat.msg.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Unbind {
    @Autowired
    private MessageSender sender;

    @Async
    public void handle(Event event){
        Text text = new Text();
        text.setToUserName(event.getFromUserName());
        text.setContent("该微信号暂未绑定Forever商户，你可以点击<a href=\"" + MessageSender.generateOAuthURL("bind") + "\">此处</a>绑定");
        MessageSender.Task task = new MessageSender.Task(MessageSender.URL_CUSTOM_MESSAGE, text, Resp.class);
        sender.send(task);
    }
}
