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
        if (jsonObject.has("access_token")) {
            this.token = jsonObject.getString("access_token");
            this.expires = jsonObject.getLong("expires_in");
        } else {
            this.object = jsonObject;
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
}