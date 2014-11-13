package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.json.JSONObject;

public interface Menu extends Response{
    public Button[] getButtons();

    public JSONObject getObject();
}
