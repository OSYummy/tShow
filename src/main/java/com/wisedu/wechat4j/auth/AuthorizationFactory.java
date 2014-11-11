package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.client.License;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.internal.http.HttpClient;

/**
 * a static factory for authorization
 */
public final class AuthorizationFactory {

    public static Authorization getInstance(Configuration conf, License license, HttpClient http){
        Authorization auth = null;
        String oauthAppId = license.getAppId();
        String oauthAppSecret = license.getAppSecret();
        if (oauthAppId!=null && oauthAppSecret!=null){
            OAuthAuthorization oauth = new OAuthAuthorization(conf, license, null);
            String accessToken = conf.getOAuthAccessToken();
            if (accessToken != null){
                oauth.setAccessToken(new AccessToken(accessToken, null));
            }
            auth = oauth;
        } else {
            auth = NullAuthorization.getInstance();
        }
        return auth;
    }
}
