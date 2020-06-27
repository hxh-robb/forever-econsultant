package com.forever.demo.wechat.msg;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图片消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Image extends Message {
    /*
    <xml>
        <ToUserName><![CDATA[toUser]]></ToUserName>
        <FromUserName><![CDATA[fromUser]]></FromUserName>
        <CreateTime>1348831860</CreateTime>
        <MsgType><![CDATA[image]]></MsgType>

        <PicUrl><![CDATA[this is a url]]></PicUrl>
        <MediaId><![CDATA[media_id]]></MediaId>
        <MsgId>1234567890123456</MsgId>
    </xml>
     */
    public Image() {
        super(MSG_TYPE_IMAGE);
    }

    @XmlCDATA @XmlElement(name = "PicUrl")
    private String picUrl;

    @XmlCDATA @XmlElement(name = "MediaId")
    private String mediaId;

    @XmlElement(name = "MsgId")
    private Long msgId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image{");
        sb.append("picUrl='").append(picUrl).append('\'');
        sb.append(", mediaId='").append(mediaId).append('\'');
        sb.append(", msgId='").append(msgId).append('\'');
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
