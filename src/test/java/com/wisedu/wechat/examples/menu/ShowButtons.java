package com.wisedu.wechat.examples.menu;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.api.Wechat;
import com.wisedu.wechat4j.client.WechatFactory;
import com.wisedu.wechat4j.entity.Button;

import java.util.List;

public class ShowButtons {
    public static void main(String[] args){
        try {
            Wechat wechat = WechatFactory.getInstance();

            List<Button> buttons = wechat.listButtons();
            for (Button button: buttons){
                System.out.println(button.toString());
            }
            System.exit(0);
        } catch (WechatException we){
            System.out.println("list buttons failed: " + we.getMessage());
            System.exit(-1);
        }
    }
}