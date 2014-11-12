package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.client.License;
import com.wisedu.wechat4j.conf.AuthorizationConfiguration;
import com.wisedu.wechat4j.internal.http.HttpClient;
import com.wisedu.wechat4j.internal.http.HttpParameter;

import java.io.IOException;
import java.io.Serializable;

public class OAuthAuthorization implements Authorization, OAuthSupport, Serializable {
    private static final long serialVersionUID = -634805421434046548L;

    private HttpClient http;
    private final AuthorizationConfiguration conf;

    private String token;
    private String appId;
    private String appSecret;
    private AccessToken oauthToken;

    public OAuthAuthorization(AuthorizationConfiguration conf, HttpClient http){
        this.conf = conf;
        this.http = http;
    }

    @Override public void setOAuthApp(String token, String appId, String appSecret){
        this.token = token!=null? token: "";
        this.appId = appId!=null? appId: "";
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
                    + "?appid=" + this.appId
                    + "&secret=" + this.appSecret
                    + "&grant_type=client_credential";
            oauthToken = new AccessToken(http.get(url));
        } catch (IOException ioe){
            throw new WechatException("Get AccessToken Failed", ioe);
        }
        return oauthToken;
    }
}