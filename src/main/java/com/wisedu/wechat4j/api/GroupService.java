package com.wisedu.wechat4j.api;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.entity.Contact;
import com.wisedu.wechat4j.entity.Response;
import com.wisedu.wechat4j.internal.json.JSONObject;

public interface GroupService {
    Contact getContact() throws WechatException;

    Contact createContact(JSONObject jsonObject) throws WechatException;

    Contact queryContact(JSONObject jsonObject) throws WechatException;

    Response updateContact(JSONObject jsonObject) throws WechatException;

    Response moveContact(JSONObject jsonObject) throws WechatException;
}
