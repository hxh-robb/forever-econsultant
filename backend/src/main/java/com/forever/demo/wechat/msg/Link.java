package com.forever.demo.wechat.msg;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 链接消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Link extends Message{
    /*
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[fromUser]]></FromUserName>
      <CreateTime>1351776360</CreateTime>
      <MsgType><![CDATA[link]]></MsgType>

      <Title><![CDATA[公众平台官网链接]]></Title>
      <Description><![CDATA[公众平台官网链接]]></Description>
      <Url><![CDATA[url]]></Url>
      <MsgId>1234567890123456</MsgId>
    </xml>
     */
    public Link() {
        super(MSG_TYPE_LINK);
    }

    @XmlElement(name = "MsgId")
    private Long msgId;

    @XmlCDATA
    @XmlElement(name = "Title")
    private String title;

    @XmlCDATA
    @XmlElement(name = "Description")
    private String description;

    @XmlCDATA
    @XmlElement(name = "Url")
    private String url;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Link{");
        sb.append("msgId=").append(msgId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
