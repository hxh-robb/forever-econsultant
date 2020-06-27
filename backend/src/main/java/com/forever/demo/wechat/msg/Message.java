package com.forever.demo.wechat.msg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微信消息
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Message {
    public static final String MSG_TYPE_UNKNOW = "?";
    public static final String MSG_TYPE_TEXT = "text";
    public static final String MSG_TYPE_IMAGE = "image";
    public static final String MSG_TYPE_VOICE = "voice";
    public static final String MSG_TYPE_VIDEO = "video";
    public static final String MSG_TYPE_SHORT_VIDEO = "shortvideo";
    public static final String MSG_TYPE_LOCATION = "location";
    public static final String MSG_TYPE_LINK = "link";
    public static final String MSG_TYPE_EVENT = "event";

    private static final HashMap<String, Class<?extends Message>> CLASSES = new HashMap<>();
    static {
        CLASSES.put(MSG_TYPE_UNKNOW, Unknown.class);
        CLASSES.put(MSG_TYPE_TEXT, Text.class);
        CLASSES.put(MSG_TYPE_IMAGE, Image.class);
        CLASSES.put(MSG_TYPE_VOICE, Voice.class);
        CLASSES.put(MSG_TYPE_VIDEO, Video.class);
        CLASSES.put(MSG_TYPE_SHORT_VIDEO, ShortVideo.class);
        CLASSES.put(MSG_TYPE_LOCATION, Location.class);
        CLASSES.put(MSG_TYPE_LINK, Link.class);
        CLASSES.put(MSG_TYPE_EVENT, Event.class);
    }

    /*
    <xml>
        <ToUserName><![CDATA[gh_dd992e99e2a5]]></ToUserName>
        <FromUserName><![CDATA[oxSXjsktzTvjrBihK-ctnxjIiXeA]]></FromUserName>
        <CreateTime>1585561331</CreateTime>
        <MsgType><![CDATA[event]]></MsgType>
        <Event><![CDATA[VIEW]]></Event>
        <EventKey><![CDATA[http://sg.robbtsang.net/wx-demo]]></EventKey>
        <MenuId>619993814</MenuId>
    </xml>
    */

    protected Message(String msgType){
        this.msgType = msgType;
        this.createTime = Long.valueOf(System.currentTimeMillis() / 1000).intValue();
    }

    @XmlCDATA
    @XmlElement(name = "ToUserName")
    @JsonProperty("touser")
    protected String toUserName;

    @XmlCDATA
    @XmlElement(name = "FromUserName")
    protected String fromUserName;

    @XmlElement(name = "CreateTime")
    @JsonIgnore
    protected Integer createTime;

    @XmlCDATA
    @XmlElement(name = "MsgType")
    @JsonProperty("msgtype")
    protected final String msgType;

    public String getToUserName() {
        return toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                '}';
    }

    public static final Message fromXML(String xml){
        Pattern msgTypePattern = Pattern.compile("<MsgType><\\s*!\\s*\\[\\s*CDATA\\s*\\[(.+)\\]\\s*\\]\\s*></MsgType>");
        Matcher matcher = msgTypePattern.matcher(xml);
        String msgType = MSG_TYPE_UNKNOW;
        if(matcher.find()){
            msgType = matcher.group(1);
        }

        Class<? extends Message> clazz = CLASSES.get(msgType);
        if(null == clazz) {
            clazz = Unknown.class;
        }

        try {
            JAXBContext context = JAXBContextFactory.createContext(new Class[]{clazz}, null);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Message message = (Message)unmarshaller.unmarshal(new StringReader(xml));
            if(message instanceof Unknown){
                ((Unknown)message).raw = xml;
            }
            return message;
        } catch (Throwable throwable) {
            LoggerFactory.getLogger(Message.class).error("Failed to decode xml", throwable);
            return new Unknown(xml);
        }
    }

    public String toXML(){
        try {
            JAXBContext context = JAXBContextFactory.createContext(new Class[]{this.getClass()}, null);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            // marshaller.setProperty(CharacterEscapeHandler.class.getName(), (CharacterEscapeHandler) (chars, i, i1, b, writer) -> writer.write(chars, i, i1));
            StringWriter sw = new StringWriter();
            marshaller.marshal(this, sw);
            return sw.toString();
        } catch (Throwable throwable) {
            LoggerFactory.getLogger(Message.class).error("Failed to encode xml", throwable);
            return null;
        }
    }

    public String toJSON(){
        try {
            ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
