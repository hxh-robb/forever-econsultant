package com.forever.demo.wechat;

import com.forever.demo.support.QueuePoller;
import com.forever.demo.wechat.handler.Subscribe;
import com.forever.demo.wechat.handler.Unbind;
import com.forever.demo.wechat.msg.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventSubscriber extends QueuePoller<Event> {
    private static final String EVT_SUBSCRIBE = "subscribe";
    private static final String EVT_CLICK = "CLICK";

    private static final String KEY_UNBIND = "UNBIND";

    public EventSubscriber() {
        super("event-subscriber-thread",  MessageReceiver.EVENTS);
    }

    @Autowired
    private Subscribe subscribe;
    @Autowired
    private Unbind unbind;

    @Override
    protected void handle(Event event) {
        if(EVT_SUBSCRIBE.equals(event.getEvent())){
            subscribe.handle(event);
        }

        if(EVT_CLICK.equals(event.getEvent()) && KEY_UNBIND.equals(event.getEventKey())){
            unbind.handle(event);
        }

        // logger.info("TODO:{}",event);
    }
}
