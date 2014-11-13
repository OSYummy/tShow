package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.client.License;
import com.wisedu.wechat4j.conf.AuthorizationConfiguration;
import com.wisedu.wechat4j.internal.http.HttpClient;

public final class AuthorizationFactory {

    public static Authorization getInstance(AuthorizationConfiguration conf, License license, HttpClient http){
        Authorization auth = null;

        String oauthToken = license.getToken();
        if (oauthToken == null){
            oauthToken = conf.getOAuthToken();
        }
        String oauthAppID = license.getAppID();
        if (oauthAppID == null){
            oauthAppID = conf.getOAuthAppID();
        }
        String oauthAppSecret = license.getAppSecret();
        if (oauthAppSecret == null){
            oauthAppSecret = conf.getOAuthAppSecret();
        }
        if (oauthToken!=null && oauthAppID!=null && oauthAppSecret!=null){
            OAuthAuthorization oauth = new OAuthAuthorization(conf, http);
            oauth.setOAuthApp(oauthToken, oauthAppID, oauthAppSecret);
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
