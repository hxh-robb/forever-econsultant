package com.forever.demo.wechat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forever.demo.support.QueuePoller;
import com.forever.demo.wechat.msg.GetAccessTokenResp;
import com.forever.demo.wechat.msg.GetOAuthAccessTokenResp;
import com.forever.demo.wechat.msg.Message;
import com.forever.demo.wechat.msg.Resp;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

@Component
public class MessageSender extends QueuePoller<MessageSender.Task> {

    /* ***** Wechat HTTP API ***** */
    public static final String URL_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={appSecret}";
    public static final String URL_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={accessToken}";
    public static final String URL_CUSTOM_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={accessToken}";
    public static final String URL_GET_OAUTH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={appSecret}&code={code}&grant_type=authorization_code";

    public static final String URL_MSGBOX = "http://sg.robbtsang.net/msgbox.html?title={0}&desc={1}&btn=%E8%BF%94%E5%9B%9E%E5%85%AC%E4%BC%97%E5%8F%B7";
    public static final String URL_OAUTH_ENTRY = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8be418543ffd11ee&redirect_uri=http%3A%2F%2Fsg.robbtsang.net%2Fwx%2Foauth&response_type=code&scope=snsapi_userinfo&state={0}#wechat_redirect";
    /* ***** File path constants ***** */
    private static final String PATH_MENU_JSON = "classpath:menu.json";

    /* ********** */

    public static final class Task {
        private String url;
        private Message requestBody;
        private Class<? extends Resp> respType;

        public Task(String url, Message requestBody, Class<? extends Resp> respType) {
            this.url = url;
            this.requestBody = requestBody;
            this.respType = respType;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Task{");
            sb.append("url='").append(url).append('\'');
            sb.append(", requestBody=").append(requestBody);
            sb.append(", respType=").append(respType);
            sb.append('}');
            return sb.toString();
        }
    }

    static final LinkedBlockingDeque<Task> TASKS = new LinkedBlockingDeque<>();

    public MessageSender() {
        super("message-sender-thread", TASKS);
    }

    public void send(Task task){
        TASKS.push(task);
    }

    /* ********** */

    private final RestTemplate httpClient =  new RestTemplateBuilder().build();

    /* ********** */

    /**
     * <a href="https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html">WeChat Get_access_token API</a>
     * @param appId
     * @param appSecret
     * @return
     */
    public String getAccessToken(String appId, String appSecret){
        try {
            logger.info("Start getting the access token");
            ResponseEntity<GetAccessTokenResp> response =
                httpClient.exchange(URL_GET_ACCESS_TOKEN, HttpMethod.GET, null, GetAccessTokenResp.class, appId, appSecret);
            if( null == response || null == response.getBody() ) {
                throw new NullPointerException("Response should not be null!");
            }

            GetAccessTokenResp resp = response.getBody();
            if( !response.getStatusCode().equals(HttpStatus.OK) || null != resp.getErrCode() ){
                logger.error("Failed to get access token, resp:[{}]", resp);
                return null;
            }
            logger.info("Got the access token:[{}]", resp);
            return resp.getAccessToken();
        } catch(Throwable throwable) {
            logger.error("Failed to request get_access_token API", throwable);
            return null;
        }
    }

    private String getMenu(){
        try{
            return new BufferedReader(new InputStreamReader(new ClassPathResource(PATH_MENU_JSON).getInputStream())).lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Throwable throwable){
            try {
                return Files.lines(Paths.get(ClassLoader.getSystemResource("menu.json").toURI()), StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
            } catch (Throwable t) {
                return null;
            }
        }
    }

    /**
     * <a href="https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html">WeChat Create_menu API</a>
     * @param accessToken
     * @return
     */
    public boolean createMenu(String accessToken){
        try {
            logger.info("Start creating WXOA menu");

            String menu = getMenu();
            if( null == menu) {
                logger.error("Could NOT get menu config");
                return false;
            }
            // String menu = new BufferedReader(new InputStreamReader(new ClassPathResource(PATH_MENU_JSON).getInputStream())).lines().collect(Collectors.joining(System.lineSeparator()));
            // String menu = Files.lines(Paths.get(ClassLoader.getSystemResource("menu.json").toURI()), StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> createMenuRequest = new HttpEntity<>(menu, headers);
            ResponseEntity<Resp> response = httpClient.exchange(URL_CREATE_MENU, HttpMethod.POST, createMenuRequest, Resp.class, accessToken);
            if( null == response || null == response.getBody() ) {
                throw new NullPointerException("Response should not be null!");
            }
            logger.info("Finished WXOA menu creation:[{}]", response.getBody());
            return response.getStatusCode().equals(HttpStatus.OK) && "0".equals(response.getBody().getErrCode());
        } catch (Throwable throwable) {
            logger.error("Failed to create WXOA menu", throwable);
            return false;
        }
    }

    /**
     * <a href="https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html">WeChat Get OAuth access token API</a>
     * @param appId
     * @param appSecret
     * @param code
     * @return
     */
    public GetOAuthAccessTokenResp getOAuthAccessToken(String appId, String appSecret, String code){
        try {
            logger.info("Start requesting OAuth access token");
            ResponseEntity<String> response =
                httpClient.exchange(URL_GET_OAUTH_ACCESS_TOKEN, HttpMethod.GET, null, String.class, appId, appSecret, code);
            if( null == response || null == response.getBody() ) {
                throw new NullPointerException("Response should not be null!");
            }

            if( !response.getStatusCode().equals(HttpStatus.OK) ){
                logger.error("Failed to get OAuth access token");
                return null;
            }

            String responseBody = response.getBody();
            logger.info(responseBody);
            GetOAuthAccessTokenResp resp = new ObjectMapper().readValue(response.getBody(), GetOAuthAccessTokenResp.class);
            logger.info("Finished requesting OAuth access token:[{}]", resp);
            return resp;
        } catch(Throwable throwable) {
            logger.error("Failed to request get_access_token API", throwable);
            return null;
        }
    }

    public final static String generateMsgboxURL(String title, String description){
        try {
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.name());
            String encodedDescription =  URLEncoder.encode(description, StandardCharsets.UTF_8.name());
            return MessageFormat.format(URL_MSGBOX, encodedTitle, encodedDescription);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public final static String generateOAuthURL(String to){
        return MessageFormat.format(URL_OAUTH_ENTRY, to);
    }

    /* ********** */

    @Override
    protected void handle(Task task) {
        if(null == AccessTokenHolder.getAccessToken()){
            logger.info("Access token is not ready yet, ignore task:", task);
            return;
        }

        try {
            logger.info("Start handling task:{}", task);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(task.requestBody.toJSON(), headers);
            ResponseEntity<? extends Resp> response = httpClient.exchange(task.url, HttpMethod.POST, requestEntity, task.respType, AccessTokenHolder.getAccessToken());
            if( null == response || null == response.getBody() ) {
                throw new NullPointerException("Response should not be null!");
            }

            Resp resp = response.getBody();
            if( !response.getStatusCode().equals(HttpStatus.OK)){
                logger.error("Failed to handle task, resp:[{}]", resp);
                return;
            }
            logger.info("Task is handled:[{}]", resp);
        } catch(Throwable throwable) {
            logger.error("Failed to handle task", throwable);
        }
    }
}
