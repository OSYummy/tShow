package com.wisedu.wechat4j.client;

import java.io.Serializable;

public final class LicenseImpl implements License, Serializable {
    private static final long serialVersionUID = -5588616181891721897L;

    private final String token;
    private final String appId;
    private final String appSecret;

    public LicenseImpl(String token, String appId, String appSecret) {
        this.token = token;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    @Override public String getToken() {
        return token;
    }

    @Override public String getAppId() {
        return appId;
    }

    @Override public String getAppSecret() {
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

        LicenseImpl that = (LicenseImpl)o;
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
