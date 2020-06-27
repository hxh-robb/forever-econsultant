package com.forever.demo.wechat.msg;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 事件消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event extends Message {
    public static final String EVT_SUBSCRIBE = "subscribe";
    public static final String EVT_UNSUBSCRIBE = "unsubscribe";
    public static final String EVT_SCAN = "SCAN";
    public static final String EVT_LOCATION = "LOCATION";
    public static final String EVT_CLICK = "CLICK";
    public static final String EVT_VIEW = "VIEW";

    /*

    // 关注/取消关注事件
    关注事件
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[FromUser]]></FromUserName>
      <CreateTime>123456789</CreateTime>
      <MsgType><![CDATA[event]]></MsgType>

      <Event><![CDATA[subscribe]]></Event>
    </xml>
    取消关注事件
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[FromUser]]></FromUserName>
      <CreateTime>123456789</CreateTime>
      <MsgType><![CDATA[event]]></MsgType>

      <Event><![CDATA[unsubscribe]]></Event>
    </xml>

    //////////////////////////////////////////////

    // 扫描带参数二维码事件
    用户未关注时，进行关注后的事件推送：
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[FromUser]]></FromUserName>
      <CreateTime>123456789</CreateTime>
      <MsgType><![CDATA[event]]></MsgType>

      <Event><![CDATA[subscribe]]></Event>
      <EventKey><![CDATA[qrscene_123123]]></EventKey>
      <Ticket><![CDATA[TICKET]]></Ticket>
    </xml>
    用户已关注时的事件推送：
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[FromUser]]></FromUserName>
      <CreateTime>123456789</CreateTime>
      <MsgType><![CDATA[event]]></MsgType>

      <Event><![CDATA[SCAN]]></Event>
      <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
      <Ticket><![CDATA[TICKET]]></Ticket>
    </xml>

    //////////////////////////////////////////////

    // 上报地理位置事件
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[fromUser]]></FromUserName>
      <CreateTime>123456789</CreateTime>
      <MsgType><![CDATA[event]]></MsgType>

      <Event><![CDATA[LOCATION]]></Event>
      <Latitude>23.137466</Latitude>
      <Longitude>113.352425</Longitude>
      <Precision>119.385040</Precision>
    </xml>

    //////////////////////////////////////////////

    // 自定义菜单事件
    点击菜单拉取消息时的事件推送
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[FromUser]]></FromUserName>
      <CreateTime>123456789</CreateTime>
      <MsgType><![CDATA[event]]></MsgType>

      <Event><![CDATA[CLICK]]></Event>
      <EventKey><![CDATA[EVENTKEY]]></EventKey>
    </xml>
    点击菜单跳转链接时的事件推送
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[FromUser]]></FromUserName>
      <CreateTime>123456789</CreateTime>
      <MsgType><![CDATA[event]]></MsgType>

      <Event><![CDATA[VIEW]]></Event>
      <EventKey><![CDATA[www.qq.com]]></EventKey>
    </xml>

     */
    public Event() {
        super(MSG_TYPE_EVENT);
    }

    @XmlCDATA @XmlElement(name = "Event")
    private String event;

    @XmlCDATA @XmlElement(name = "EventKey")
    private String eventKey;

    @XmlCDATA @XmlElement(name = "Ticket")
    private String ticket;

    @XmlElement(name = "Latitude")
    private Double latitude;

    @XmlElement(name = "Longitude")
    private Double longitude;

    @XmlElement(name = "Precision")
    private Double precision;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("event='").append(event).append('\'');
        sb.append(", eventKey='").append(eventKey).append('\'');
        sb.append(", ticket='").append(ticket).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", precision=").append(precision);
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
