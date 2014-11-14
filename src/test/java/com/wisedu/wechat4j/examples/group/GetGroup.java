package com.wisedu.wechat4j.examples.group;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.client.Wechat;
import com.wisedu.wechat4j.entity.Contact;

public class GetGroup {
    public static void main(String args) {
        try {
            Wechat wechat = Wechat.newInstance();
            Contact contact = wechat.getContact();
            if (contact.getResponse().getErrCode() == null) {
                System.out.println("Get Contact Succeed: " + contact);
            } else {
                System.err.println("Get Contact failed: " + contact);
            }
        } catch (WechatException we) {
            we.printStackTrace();
        }
    }
}
