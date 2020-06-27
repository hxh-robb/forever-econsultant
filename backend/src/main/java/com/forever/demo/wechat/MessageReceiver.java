package com.forever.demo.wechat;

import com.forever.demo.wechat.msg.Event;
import com.forever.demo.wechat.msg.Message;
import com.forever.demo.wechat.msg.Text;
import com.forever.demo.wechat.msg.Unknown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.LinkedBlockingDeque;

@Component @Path("/wechat")
public class MessageReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);
    static final LinkedBlockingDeque<Message> MESSAGES = new LinkedBlockingDeque<>();
    static final LinkedBlockingDeque<Event> EVENTS = new LinkedBlockingDeque<>();

    @POST @Consumes({MediaType.TEXT_XML})
    public Response addToTaskQueue(@Context HttpHeaders headers, String xml){
        Message incoming = Message.fromXML(xml);
        LOGGER.info("Received message:[{}]", incoming);

        if(incoming instanceof Unknown){
            return Response.ok().
                header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_TYPE.withCharset("UTF-8").toString()).
                entity(new Text(incoming,"未能识别本次请求。").toXML()).
                build();
        }

        if(incoming instanceof Event){
            EVENTS.add((Event)incoming);
            return Response.ok().
                header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_TYPE.withCharset("UTF-8").toString()).
                // entity(new Text(incoming,"请求已受理，请稍候。").toXML()).
                build();
        }

        // MESSAGES.add((Message)incoming);
        return Response.ok().
                header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_TYPE.withCharset("UTF-8").toString()).
                entity(new Text(incoming,"暂未开通人工客服功能，请通过功能菜单完成自助服务。").toXML()).
                build();
    }
}
