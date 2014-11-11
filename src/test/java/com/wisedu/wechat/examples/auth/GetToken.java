package com.wisedu.wechat.examples.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.api.Wechat;
import com.wisedu.wechat4j.auth.AccessToken;
import com.wisedu.wechat4j.client.License;
import com.wisedu.wechat4j.client.WechatFactory;

public class GetToken {
    public static void main(String[] args){
        License license = new License() {
            @Override
            public String getToken() {
                return null;
            }

            @Override
            public String getAppId() {
                return null;
            }

            @Override
            public String getAppSecret() {
                return null;
            }
        };

        Wechat wechat = WechatFactory.getInstance(license);
        try {
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