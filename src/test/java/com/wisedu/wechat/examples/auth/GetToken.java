package com.wisedu.wechat.examples.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.auth.AccessToken;
import com.wisedu.wechat4j.client.Wechat;

public class GetToken {
    public static void main(String[] args){
        try {
            Wechat wechat = Wechat.newInstance();
            AccessToken token = wechat.getAccessToken();
            if (token != null){
                System.out.println(token);
            } else {
                System.out.println("Fail to Get Access Token");
            }
        } catch (WechatException we){
            we.printStackTrace();
        }
    }
}