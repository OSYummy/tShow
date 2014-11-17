package com.wisedu.wechat4j.examples.group;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.client.Wechat;
import com.wisedu.wechat4j.entity.Contact;
import com.wisedu.wechat4j.internal.json.JSONObject;

public class CreateGroup {
    public static void main(String[] args) {
        try {
            Wechat wechat = Wechat.newInstance();
            Contact contact = wechat.createContact(new JSONObject("{\"group\":{\"name\":\"test\"}}"));
            if (contact.getResponse().getErrCode() == null) {
                System.out.println("Create Group Succeed: " + contact);
            } else {
                System.err.println("Create Group Failed: " + contact);
            }
        } catch (WechatException we) {
            we.printStackTrace();
        }
    }
}
