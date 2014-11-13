package com.wisedu.wechat4j.examples.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.auth.AccessToken;
import com.wisedu.wechat4j.client.Wechat;

public class GetToken {
    public static void main(String[] args){
        try {
            Wechat wechat = Wechat.newInstance();
            AccessToken token = wechat.getAccessToken();
            if (token.getErrCode() == null){
                System.out.println("Get Token Succeed." + token);
            } else {
                System.err.println("Get Token Failed." + token);
            }
        } catch (WechatException we){
            we.printStackTrace();
        }
    }
}