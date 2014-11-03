package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.client.License;
import com.wisedu.wechat4j.conf.AuthorizationConfiguration;
import com.wisedu.wechat4j.internal.http.HttpClient;
import com.wisedu.wechat4j.internal.http.HttpParameter;
import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.util.W4JLRUCache;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

public class OAuthAuthorization implements Authorization, OAuthSupport, Serializable {
    private static final long serialVersionUID = -634805421434046548L;

    private HttpClient http;
    private final License license;
    private final AuthorizationConfiguration conf;

    private String appId;
    private String appSecret;
    private AccessToken accessToken;

    private static final W4JLRUCache<String, AccessToken> appTokenMap = new W4JLRUCache<String, AccessToken>(20);

    public OAuthAuthorization(AuthorizationConfiguration conf, License license, HttpClient http){
        this.conf = conf;
        this.http = http;
        this.license = license;
    }

    @Override public void setOAuthApp(String appId, String appSecret){
        this.appId = appId!=null? appId: "";
        this.appSecret = appSecret!=null? appSecret: "";
    }

    @Override public void setAccessToken(AccessToken accessToken) {
    }

    @Override public HttpParameter[] generateAuthorizationParameter(){
        AccessToken accessToken = null;
        try {
            accessToken = getAccessToken();
        } catch (WechatException we){
        }

        if (accessToken != null) {
            return new HttpParameter[]{
                    new HttpParameter("access_token", accessToken.getToken())
            };
        }
        return new HttpParameter[]{};
    }

    @Override public boolean isEnabled(){
        try {
            return getAccessToken()!=null;
        } catch (WechatException we){
        }
        return false;
    }

    @Override public AccessToken getAccessToken() throws WechatException{
        long interval=0, expires=0;
        AccessToken accessToken = appTokenMap.get(appId);
        if (accessToken != null){
            expires = accessToken.getExpires();
            interval = System.currentTimeMillis() - accessToken.getTimestamp();
        }

        if (interval<<2 >= expires){
            try {
                String url = conf.getOAuthAccessTokenURL()
                        + "?appid=" + this.appId
                        + "&secret=" + this.appSecret
                        + "&grant_type=client_credential";
                HttpResponse response = http.get(url);
                if (response.getStatusCode() != 200){
                    throw new WechatException("authorization failed.");
                }
                accessToken = new AccessToken(response);
                appTokenMap.put(appId, accessToken);
            } catch (IOException ioe){
                throw new WechatException("Get access token failed", ioe);
            }
        }

        return accessToken;
    }
}