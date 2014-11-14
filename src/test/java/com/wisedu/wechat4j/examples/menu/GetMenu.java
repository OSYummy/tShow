package com.wisedu.wechat4j.examples.menu;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.client.Wechat;
import com.wisedu.wechat4j.entity.Menu;
import com.wisedu.wechat4j.entity.Response;

public class GetMenu {
    public static void main(String[] args){
        try {
            Wechat wechat = Wechat.newInstance();
            Menu menu = wechat.getMenu();
            if (menu.getResponse().getErrCode() == null) {
                System.out.println("Get Menu Succeed: " + menu);
            } else {
                System.err.println("Get Menu failed: " + menu);
            }
        } catch (WechatException we) {
            we.printStackTrace();
        }
    }
}
