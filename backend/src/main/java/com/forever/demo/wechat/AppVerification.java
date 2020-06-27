package com.forever.demo.wechat;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component @Path("/wechat")
public class AppVerification {
    @Value("${wechat.app.id}")
    private String id;

    @Value("${wechat.app.secret}")
    private String secret;

    @Value("${wechat.app.token}")
    private String token;


    @GET
    public String verify(
        @NotNull  @QueryParam("signature") String signature,
        @NotNull @QueryParam("timestamp") String timestamp,
        @NotNull @QueryParam("nonce") String nonce,
        @NotNull  @QueryParam("echostr") String echostr
    ){
        List<String> list = Arrays.asList(token, timestamp, nonce);
        Collections.sort(list);
        String hashcode = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            for (String item:list) {
                md.update(item.getBytes());
            }
            hashcode = Hex.encodeHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            LoggerFactory.getLogger(AppVerification.class).error(e.getMessage(), e);
        }

        LoggerFactory.getLogger(AppVerification.class).info("Incoming signature:[" + signature + "]");
        LoggerFactory.getLogger(AppVerification.class).info("Generated hashcode:[" + hashcode + "]");

        if(signature.equalsIgnoreCase(hashcode)){
            return echostr;
        } else {
            return "";
        }
    }
}
