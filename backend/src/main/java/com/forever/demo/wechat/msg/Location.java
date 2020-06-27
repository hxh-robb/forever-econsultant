package com.forever.demo.wechat.msg;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 地理位置消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location extends Message {
    /*
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[fromUser]]></FromUserName>
      <CreateTime>1351776360</CreateTime>
      <MsgType><![CDATA[location]]></MsgType>

      <Location_X>23.134521</Location_X>
      <Location_Y>113.358803</Location_Y>
      <Scale>20</Scale>
      <Label><![CDATA[位置信息]]></Label>
      <MsgId>1234567890123456</MsgId>
    </xml>
     */
    public Location() {
        super(MSG_TYPE_LOCATION);
    }

    @XmlElement(name = "MsgId")
    private Long msgId;

    @XmlCDATA
    @XmlElement(name = "Label")
    private String label;

    @XmlElement(name = "Location_X")
    private Double x;

    @XmlElement(name = "Location_Y")
    private Double y;

    @XmlElement(name = "Scale")
    private Integer scale;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("msgId=").append(msgId);
        sb.append(", label='").append(label).append('\'');
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", scale=").append(scale);
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
