package com.wisedu.wechat4j;

public class WechatException extends Exception {

    public WechatException(String message) {
        this(message, null);
    }

    public WechatException(Throwable cause) {
        super(cause);
    }

    public WechatException(String message, Throwable cause){
        super(message, cause);
    }
}
