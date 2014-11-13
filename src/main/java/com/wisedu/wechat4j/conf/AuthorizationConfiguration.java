package com.wisedu.wechat4j.conf;

public interface AuthorizationConfiguration {
    String getOAuthToken();

    String getOAuthAppID();

    String getOAuthAppSecret();

    String getOAuthAccessToken();

    String getOAuthAccessTokenURL();
}
