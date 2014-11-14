package com.wisedu.wechat4j.examples.menu;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.client.Wechat;
import com.wisedu.wechat4j.entity.Response;

public class DeleteMenu {
    public static void main(String[] args) {
        try {
            Wechat wechat = Wechat.newInstance();
            Response response = wechat.deleteMenu();
            if (response.getErrCode() == 0) {
                System.out.println("Delete Menu Succeed: " + response);
            } else {
                System.err.println("Delete Menu Failed: " + response);
            }
        } catch (WechatException we) {
            we.printStackTrace();
        }
    }
}
