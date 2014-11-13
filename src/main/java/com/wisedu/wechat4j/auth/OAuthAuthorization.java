package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.conf.AuthorizationConfiguration;
import com.wisedu.wechat4j.internal.http.HttpClient;
import com.wisedu.wechat4j.internal.http.HttpParameter;

import java.io.IOException;
import java.io.Serializable;

public final class OAuthAuthorization implements Authorization, OAuthSupport, Serializable {
    private static final long serialVersionUID = -634805421434046548L;

    private HttpClient http;
    private final AuthorizationConfiguration conf;

    private String token;
    private String appID;
    private String appSecret;
    private AccessToken oauthToken;

    public OAuthAuthorization(AuthorizationConfiguration conf, HttpClient http){
        this.conf = conf;
        this.http = http;
    }

    @Override public String getAppID() {
        return appID;
    }

    @Override public String getAppSecret() {
        return appSecret;
    }

    @Override public void setOAuthApp(String token, String appID, String appSecret){
        this.token = token!=null? token: "";
        this.appID = appID!=null? appID: "";
        this.appSecret = appSecret!=null? appSecret: "";
    }

    @Override public void setAccessToken(AccessToken accessToken) {
        this.oauthToken = accessToken;
    }

    @Override public HttpParameter[] generateAuthorizationParameter(){
        if (oauthToken != null){
            return new HttpParameter[]{
                    new HttpParameter("access_token", oauthToken.getToken())
            };
        }
        return null;
    }

    @Override public boolean isEnabled(){
        return oauthToken != null;
    }

    @Override public AccessToken getAccessToken() throws WechatException{
        try {
            String url = conf.getOAuthAccessTokenURL()
                    + "?appid=" + this.appID
                    + "&secret=" + this.appSecret
                    + "&grant_type=client_credential";
            this.oauthToken = new AccessToken(http.get(url));
        } catch (IOException ioe){
            throw new WechatException("Get AccessToken Failed", ioe);
        }
        return oauthToken;
    }
}