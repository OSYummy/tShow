package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

final class AccessTokenJSONImpl extends ResponseJSONImpl implements AccessToken, Serializable {
    private static final long serialVersionUID = 5756387504195451360L;

    private String token;
    private Long expires;

    private JSONObject object;

    AccessTokenJSONImpl(HttpResponse response) throws IOException {
        this(response.asJSONObject());
    }

    AccessTokenJSONImpl(JSONObject jsonObject) {
        super(jsonObject);
        this.object = jsonObject;
        this.expires = object.has("expires_in")? object.getLong("expires_in"): null;
        this.token = object.has("access_token")? object.getString("access_token"): null;
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

    @Override public String toString() {
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