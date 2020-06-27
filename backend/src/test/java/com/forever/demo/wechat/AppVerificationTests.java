package com.forever.demo.wechat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppVerificationTests {
    @Autowired
    private AppVerification appVerification;

    @Test
    public void testVerify() {
        /* Test Data
        signature= 96d72f1130f51b8e6b181d81e1d28f953e045784
        timestmap= 1585529206
        nonce= 106882970
        token= 70669455
        echostr= 4392537951384491382
        unsorted list= ['70669455', u'1585529206', u'106882970']
        sorted list= [u'106882970', u'1585529206', '70669455']
         */
        String echostr = appVerification.verify("96d72f1130f51b8e6b181d81e1d28f953e045784","1585529206","106882970" ,"4392537951384491382");
        Assertions.assertEquals("4392537951384491382", echostr);
    }
}
