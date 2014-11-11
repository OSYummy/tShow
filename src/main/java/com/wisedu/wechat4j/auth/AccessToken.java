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
}
