package com.wisedu.wechat4j;

import org.json.JSONObject;

public class WechatException extends Exception {
    private int errorCode = -1;
    private String errorMessage = null;

    public WechatException(String message) {
        this(message, null);
    }

    public WechatException(Throwable cause) {
        super(cause);
    }

    public WechatException(JSONObject jsonObject){
        this.errorCode = jsonObject.getInt("errcode");
        this.errorMessage = jsonObject.getString("errmsg");
    }

    public WechatException(String message, Throwable cause){
        super(message, cause);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
