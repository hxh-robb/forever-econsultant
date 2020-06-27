package com.forever.demo.wechat.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 未识别消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Unknown extends Message{
    String raw;

    public Unknown() {
        super(MSG_TYPE_UNKNOW);
    }

    public Unknown(String raw) {
        this();
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }

    @Override
    public String getMsgType() {
        return MSG_TYPE_UNKNOW;
    }

    public String getActualMsgType() {
        return super.getMsgType();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Unknow{");
        sb.append("raw='").append(raw).append('\'');
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
