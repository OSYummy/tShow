package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.WechatException;

public interface OAuthSupport {
    /**
     * sets the OAuth AppID and App secret
     *
     * @param token OAuth Token
     * @param appId OAuth AppID
     * @param appSecret OAuth App secret
     * @throws IllegalStateException when OAuth AppId has already been set
     */
    void setOAuthApp(String token, String appId, String appSecret);

    /**
     * set the access token
     *
     * @param accessToken access token
     */
    void setAccessToken(AccessToken accessToken);

    AccessToken getAccessToken() throws WechatException;
}
