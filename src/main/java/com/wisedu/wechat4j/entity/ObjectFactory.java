package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.internal.http.HttpResponse;

import java.io.Serializable;
import java.util.List;

public interface ObjectFactory {
    AccessToken createAccessToken(HttpResponse response) throws WechatException;

    Menu createMenu(HttpResponse response) throws WechatException;
}
