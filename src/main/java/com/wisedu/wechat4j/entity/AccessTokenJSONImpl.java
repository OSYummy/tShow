package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.Serializable;

final class AccessTokenJSONImpl extends ResponseJSONImpl implements AccessToken, Serializable {
    private static final long serialVersionUID = 5756387504195451360L;

    AccessTokenJSONImpl(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override public String getToken() {
        return null;
    }

    @Override public Long getExpires() {
        return null;
    }

    @Override public JSONObject getObject() {
        return null;
    }
}
