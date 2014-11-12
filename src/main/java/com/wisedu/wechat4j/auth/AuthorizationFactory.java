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

        String oauthToken = license.getToken();
        if (oauthToken == null){
            oauthToken = conf.getOAuthToken();
        }
        String oauthAppId = license.getAppId();
        if (oauthAppId == null){
            oauthAppId = conf.getOAuthAppId();
        }
        String oauthAppSecret = license.getAppSecret();
        if (oauthAppSecret == null){
            oauthAppSecret = conf.getOAuthAppSecret();
        }
        if (oauthToken!=null && oauthAppId!=null && oauthAppSecret!=null){
            OAuthAuthorization oauth = new OAuthAuthorization(conf, http);
            oauth.setOAuthApp(oauthToken, oauthAppId, oauthAppSecret);
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
