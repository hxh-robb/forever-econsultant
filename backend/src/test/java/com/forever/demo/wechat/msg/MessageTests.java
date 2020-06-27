package com.forever.demo.wechat.msg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageTests {
    @Test
    public void testCreateTextFromXML() {
        String xml = "<xml><ToUserName><![CDATA[gh_dd992e99e2a5]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oxSXjsktzTvjrBihK-ctnxjIiXeA]]></FromUserName>\n" +
                "<CreateTime>1585622946</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[test]]></Content>\n" +
                "<MsgId>22700661818786461</MsgId>\n" +
                "</xml>\n";

        Message message = Message.fromXML(xml);
        Assertions.assertEquals(Text.class, message.getClass());
        System.out.println(message);
        // TODO
    }
    @Test
    public void testTextToXML() {
        String xml = "<xml><ToUserName><![CDATA[gh_dd992e99e2a5]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oxSXjsktzTvjrBihK-ctnxjIiXeA]]></FromUserName>\n" +
                "<CreateTime>1585622946</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[test]]></Content>\n" +
                "<MsgId>22700661818786461</MsgId>\n" +
                "</xml>\n";

        Text outgoing = new Text();
        outgoing.setToUserName("gh_dd992e99e2a5");
        outgoing.setFromUserName("oxSXjsktzTvjrBihK-ctnxjIiXeA");
        outgoing.setCreateTime(1585622946);
        outgoing.setContent("功能尚未开通,敬请<a href=\"https://www.qq.com\">期待</a>");
        System.out.println(outgoing.toXML());
    }

    @Test
    public void testCreateUnknowFromXML() {
        String xml = "<xml>\n" +
                "      <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "      <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "      <CreateTime>1357290913</CreateTime>\n" +
                "      <MsgType><![CDATA[voice1]]></MsgType>\n" +
                "      <MediaId><![CDATA[media_id]]></MediaId>\n" +
                "      <Format><![CDATA[Format]]></Format>\n" +
                "      <Recognition><![CDATA[腾讯微信团队]]></Recognition>\n" +
                "      <MsgId>1234567890123456</MsgId>\n" +
                "    </xml>";

        Message message = Message.fromXML(xml);
        Assertions.assertEquals(Unknown.class, message.getClass());
        System.out.println(message);
        // TODO
    }
}
