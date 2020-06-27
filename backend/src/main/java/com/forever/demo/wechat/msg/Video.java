package com.forever.demo.wechat.msg;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 视频消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Video extends Message {
    /*
    <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[fromUser]]></FromUserName>
      <CreateTime>1357290913</CreateTime>
      <MsgType><![CDATA[video]]></MsgType>

      <MediaId><![CDATA[media_id]]></MediaId>
      <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
      <MsgId>1234567890123456</MsgId>
    </xml>
     */
    public Video() {
        super(MSG_TYPE_VIDEO);
    }

    @XmlCDATA
    @XmlElement(name = "MediaId")
    private String mediaId;

    @XmlElement(name = "MsgId")
    private Long msgId;

    @XmlCDATA
    @XmlElement(name = "ThumbMediaId")
    private String thumbMediaId;

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

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Video{");
        sb.append("mediaId='").append(mediaId).append('\'');
        sb.append(", msgId='").append(msgId).append('\'');
        sb.append(", thumbMediaId='").append(thumbMediaId).append('\'');
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
