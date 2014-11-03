package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.client.License;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.internal.http.HttpClient;

public final class AuthorizationFactory {
    public static Authorization getInstance(Configuration conf, License license, HttpClient http){
        String oauthAppId = license.getAppId();
        String oauthAppSecret = license.getAppSecret();
        if (oauthAppId!=null && oauthAppSecret!=null){
            return new OAuthAuthorization(conf, license, http);
        } else {
            return NullAuthorization.getInstance();
        }
    }
}
