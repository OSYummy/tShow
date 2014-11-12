package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;

public class AccessToken {
    private String token;
    private Long expires;

    private JSONObject object;

    public AccessToken(HttpResponse response) throws IOException {
        this(response.asJSONObject());
    }

    public AccessToken(JSONObject jsonObject) {
        if (jsonObject.has("access_token")) {
            this.token = jsonObject.getString("access_token");
            this.expires = jsonObject.getLong("expires_in");
        } else {
            this.object = jsonObject;
        }
    }

    public AccessToken(String token, Long expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public long getExpires() {
        return expires;
    }

    public JSONObject getObject(){
        return object;
    }

    @Override public int hashCode() {
        int result = 0;
        result = 31*result + (token!=null? token.hashCode(): 0);
        result = 31*result + (expires!=null? expires.hashCode(): 0);
        result = 31*result + (object!=null? object.hashCode(): 0);
        return result;
    }

    @Override public boolean equals(Object o){
        if (this == o) return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;

        AccessToken that = (AccessToken)o;
        if (token!=null? !token.equals(that.token): that.token!=null)
            return false;
        if (expires!=null? !expires.equals(that.expires): that.expires!=null)
            return false;
        if (object!=null? !object.equals(that.object): that.object!=null)
            return false;

        return true;
    }

    @Override public String toString(){
        if (object == null){
            return "{"
                    + "\"access_token\": " + token + ", "
                    + "\"expires\": " + expires
                    + "}";
        } else {
            return object.toString();
        }
    }
}
