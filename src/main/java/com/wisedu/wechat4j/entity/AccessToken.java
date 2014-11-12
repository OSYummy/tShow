package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.json.JSONObject;

public interface AccessToken extends Response{
    String getToken();

    Long getExpires();

    JSONObject getObject();
}
