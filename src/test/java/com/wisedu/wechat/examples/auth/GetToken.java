package com.wisedu.wechat.examples.auth;

import com.wisedu.wechat4j.api.Wechat;
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
    }
}