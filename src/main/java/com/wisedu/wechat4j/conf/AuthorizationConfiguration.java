package com.wisedu.wechat4j.conf;

public interface AuthorizationConfiguration {
    String getOAuthToken();

    String getOAuthAppId();

    String getOAuthAppSecret();

    String getOAuthAccessTokenURL();
}
