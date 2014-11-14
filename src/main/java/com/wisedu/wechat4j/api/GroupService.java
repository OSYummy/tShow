package com.wisedu.wechat4j.api;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.entity.Contact;

public interface GroupService {
    Contact getContact() throws WechatException;
}
