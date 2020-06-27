package com.forever.demo.wechat.msg;

public class Resp {
    protected String errCode;
    protected String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Resp{");
        sb.append("errCode='").append(errCode).append('\'');
        sb.append(", errMsg='").append(errMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void setErrcode(String errCode) {
        this.setErrCode(errCode);
    }

    public void setErrmsg(String errMsg) {
        setErrMsg(errMsg);
    }
}
