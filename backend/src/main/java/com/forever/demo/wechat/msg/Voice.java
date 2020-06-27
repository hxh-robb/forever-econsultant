package com.forever.demo.wechat.msg;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 语音消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Voice extends Message {
    /*
    <xml>
      <ToUserName>< ![CDATA[toUser] ]></ToUserName>
      <FromUserName>< ![CDATA[fromUser] ]></FromUserName>
      <CreateTime>1357290913</CreateTime>
      <MsgType>< ![CDATA[voice] ]></MsgType>

      <MediaId>< ![CDATA[media_id] ]></MediaId>
      <Format>< ![CDATA[Format] ]></Format>
      <Recognition>< ![CDATA[腾讯微信团队] ]></Recognition>
      <MsgId>1234567890123456</MsgId>
    </xml>
    */
    public Voice() {
        super(MSG_TYPE_VOICE);
    }

    @XmlCDATA @XmlElement(name = "MediaId")
    private String mediaId;

    @XmlElement(name = "MsgId")
    private Long msgId;

    @XmlCDATA @XmlElement(name = "Recognition")
    private String recognition;

    @XmlCDATA @XmlElement(name = "Format")
    private String format;

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

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Voice{");
        sb.append("mediaId='").append(mediaId).append('\'');
        sb.append(", msgId='").append(msgId).append('\'');
        sb.append(", recognition='").append(recognition).append('\'');
        sb.append(", format='").append(format).append('\'');
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
