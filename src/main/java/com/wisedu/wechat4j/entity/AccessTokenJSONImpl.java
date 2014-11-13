package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

final class AccessTokenJSONImpl extends ResponseJSONImpl implements AccessToken, Serializable {
    private static final long serialVersionUID = 1921423287946569626L;
    private String token;
    private Long expires;

    private JSONObject object;

    AccessTokenJSONImpl(HttpResponse response) throws IOException {
        this(response.asJSONObject());
    }

    AccessTokenJSONImpl(JSONObject jsonObject) {
        super(jsonObject);
        init(jsonObject);
    }

    private void init(JSONObject jsonObject) {
        this.object = jsonObject;
        if (!object.isNull("access_token")) {
            this.token = object.getString("access_token");
        }
        if (!object.isNull("expires_in")) {
            this.expires = object.getLong("expires_in");
        }
    }

    @Override public String getToken() {
        return token;
    }

    @Override public Long getExpires() {
        return expires;
    }

    @Override public JSONObject getObject() {
        return object;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;
        if (!super.equals(o)) return false;

        AccessTokenJSONImpl that = (AccessTokenJSONImpl)o;

        if (token!= null? !token.equals(that.token): that.token!=null)
            return false;
        if (expires!= null? !expires.equals(that.expires): that.expires!=null)
            return false;
        if (object!= null? !object.equals(that.object): that.object!=null)
            return false;

        return true;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31*result + (token!=null? token.hashCode(): 0);
        result = 31*result + (expires!=null? expires.hashCode(): 0);
        result = 31*result + (object!=null? object.hashCode(): 0);
        return result;
    }

    @Override public String toString() {
        if (object != null){
            return object.toString();
        } else {
            return "{}";
        }
    }
}