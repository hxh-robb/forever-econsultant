package com.forever.demo.wechat.msg;

public class GetOAuthAccessTokenResp extends Resp {
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private String openId;
    private String scope;

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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetOAuthAccessTokenResp{");
        sb.append("accessToken='").append(accessToken).append('\'');
        sb.append(", expiresIn=").append(expiresIn);
        sb.append(", refreshToken='").append(refreshToken).append('\'');
        sb.append(", openId='").append(openId).append('\'');
        sb.append(", scope='").append(scope).append('\'');
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

    public void setRefresh_token(String refreshToken) {
        setRefreshToken(refreshToken);
    }

    public void setOpenid(String openId) {
        setOpenId(openId);
    }
}
