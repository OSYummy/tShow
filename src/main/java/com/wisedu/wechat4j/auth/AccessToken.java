package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.api.Wechat;
import com.wisedu.wechat4j.internal.http.HttpResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

public class AccessToken implements Serializable {
    private String token;
    private long expires;
    private long timestamp = System.currentTimeMillis();

    public AccessToken(HttpResponse response) throws WechatException{
        try {
            JSONObject jsonObject = response.asJSONObject();
            if (null != jsonObject.getString("access_token")) {
                this.token = jsonObject.getString("access_token");
                this.expires = jsonObject.getInt("expires_in");
            } else {
                throw new WechatException(jsonObject);
            }
        } catch (IOException ioe){
            throw new WechatException("Initialized fail", ioe);
        }
    }

    public AccessToken(String token, long expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public long getExpires() {
        return expires;
    }

    public long getTimestamp(){
        return timestamp;
    }
}
