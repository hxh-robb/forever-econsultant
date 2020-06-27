package com.forever.demo.wechat.msg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.*;

/**
 * 文本消息
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Text extends Message {
    /*
    <xml>
        <ToUserName><![CDATA[gh_dd992e99e2a5]]></ToUserName>
        <FromUserName><![CDATA[oxSXjsktzTvjrBihK-ctnxjIiXeA]]></FromUserName>
        <CreateTime>1585622946</CreateTime>
        <MsgType><![CDATA[text]]></MsgType>

        <Content><![CDATA[test]]></Content>
        <MsgId>22700661818786461</MsgId>
    </xml>
    */
    public Text() {
        super(MSG_TYPE_TEXT);
    }

    public Text(Message request, String reponse) {
        this();
        if(null != request){
            this.setToUserName(request.getFromUserName());
            this.setFromUserName(request.getToUserName());
        }
        this.setContent(reponse);
    }

    @XmlCDATA
    @XmlElement(name = "Content")
    @JsonIgnore
    private String content;

    @XmlTransient @JsonProperty
    private final Wrapper text = new Wrapper();

    @XmlElement(name = "MsgId")
    private Long msgId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.text.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Text{");
        sb.append("content='").append(content).append('\'');
        sb.append(", msgId='").append(msgId).append('\'');
        sb.append(", toUserName='").append(toUserName).append('\'');
        sb.append(", fromUserName='").append(fromUserName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", msgType='").append(msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private static final class Wrapper {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
