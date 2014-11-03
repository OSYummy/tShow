package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.internal.http.HttpResponse;

import java.util.List;

public class JSONImplFactory implements ObjectFactory{
    @Override public List<Button> createButtonList(HttpResponse response) throws WechatException{
        return ButtonJSONImpl.createButtonList(response);
    }
}
