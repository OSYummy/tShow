package com.wisedu.wechat4j.client;

import java.io.Serializable;

public final class License implements Serializable {
    private static final long serialVersionUID = 3030137592761879429L;

    private final String token;
    private final String appId;
    private final String appSecret;

    public License(String token, String appId, String appSecret) {
        this.token = token;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    @Override public int hashCode(){
        int result = 0;
        result = result*31 + (token!=null? token.hashCode(): 0);
        result = result*31 + (appId!=null? appId.hashCode(): 0);
        result = result*31 + (token!=null? appSecret.hashCode(): 0);
        return result;
    }

    @Override public boolean equals(Object o){
        if (o == this) return true;
        if (o==null || o.getClass()!=this.getClass()) return false;

        License that = (License)o;
        if (token!=null? !token.equals(that.token): that.token!=null)
            return false;
        if (appId!=null? !appId.equals(that.appId): that.appId!=null)
            return false;
        if (appSecret!=null? !appSecret.equals(that.appSecret): that.appSecret!=null)
            return false;
        return true;
    }

    @Override public String toString(){
        return "{"
                + "token=" + token + ","
                + "appId=" + appId + ","
                + "appSecret=" + appSecret + ","
                + "}";
    }
}
