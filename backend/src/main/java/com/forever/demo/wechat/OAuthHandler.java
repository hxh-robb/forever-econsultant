package com.forever.demo.wechat;

import com.forever.demo.wechat.msg.GetOAuthAccessTokenResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.text.MessageFormat;

@Component @Path("/wechat/oauth")
public class OAuthHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthHandler.class);

    @Value("${wechat.app.id}")
    private String id;

    @Value("${wechat.app.secret}")
    private String secret;

    @Autowired
    private MessageSender sender;

    @GET
    public Response handleCode(
      @Context HttpServletRequest request,
      @NotNull @QueryParam("code") String code,
      @NotNull @QueryParam("state") String to
    ){
        LOGGER.info("code:[{}], to:[{}]",code, to);
        GetOAuthAccessTokenResp resp = sender.getOAuthAccessToken(id,secret,code);
        if(null == resp || null != resp.getErrCode()){
            String msgbox = MessageSender.generateMsgboxURL("授权失败", MessageFormat.format("无法获取微信授权码，错误码：{0}", resp.getErrCode()));
            return Response.seeOther(URI.create(msgbox)).build();
        }

        LOGGER.info(resp.toString());
        HttpSession session = request.getSession(true);
        session.setAttribute("wxOAuthAccessToken", resp.getAccessToken());
        session.setAttribute("wxOAuthExpireIn", resp.getExpiresIn());
        session.setAttribute("wxOAuthOpenId", resp.getOpenId());
        session.setAttribute("wxOAuthRefreshToken", resp.getRefreshToken());
        session.setAttribute("wxOAuthScope", resp.getScope());

        // https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
        return Response.seeOther(URI.create("http://sg.robbtsang.net/" + to + ".html")).build();
    }
}
