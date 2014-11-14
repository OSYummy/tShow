package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.internal.json.JSONArray;
import com.wisedu.wechat4j.internal.json.JSONObject;

public interface Response {
    JSONObject getObject();

    JSONArray getArray();

    Integer getErrCode();

    String getErrMsg();
}
