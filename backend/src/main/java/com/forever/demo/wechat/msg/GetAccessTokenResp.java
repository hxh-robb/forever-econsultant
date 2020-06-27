package com.forever.demo.wechat.msg;

public class GetAccessTokenResp extends Resp {

    private String accessToken;
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAccessTokenResp{");
        sb.append("accessToken='").append(accessToken).append('\'');
        sb.append(", expiresIn='").append(expiresIn).append('\'');
        sb.append(", errCode='").append(errCode).append('\'');
        sb.append(", errMsg='").append(errMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void setAccess_token(String accessToken) {
        setAccessToken(accessToken);
    }

    public void setExpires_in(Integer expiresIn) {
        setExpiresIn(expiresIn);
    }
}
