package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.auth.*;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.entity.JSONImplFactory;
import com.wisedu.wechat4j.entity.ObjectFactory;
import com.wisedu.wechat4j.internal.http.HttpClient;
import com.wisedu.wechat4j.internal.http.HttpClientFactory;


abstract class WechatBaseImpl extends Wechat implements WechatBase{
    protected Configuration conf;
    protected Authorization auth;
    protected HttpClient http;
    protected ObjectFactory factory;

    WechatBaseImpl(Configuration conf, License license){
        this.conf = conf;
        this.http = HttpClientFactory.getInstance(conf);
        this.auth = AuthorizationFactory.getInstance(conf, license, http);
        this.factory = new JSONImplFactory();
    }

    @Override public String getAppID() {
        return getOAuth().getAppID();
    }

    @Override public String getAppSecret() {
        return getOAuth().getAppSecret();
    }

    @Override public void setOAuthApp(String token, String appId, String appSecret){
        if (appId == null){
            throw new NullPointerException("app id is null");
        }
        if (appSecret == null){
            throw new NullPointerException("app secret is null");
        }

        if (auth instanceof NullAuthorization){
            Authorization auth = AuthorizationFactory.getInstance(
                    conf, new License(token, appId, appSecret), http
            );
            this.auth = auth;
        } else if (auth instanceof  OAuthAuthorization){
            throw new IllegalStateException("app id/secret pair already set.");
        }
    }

    @Override public void setAccessToken(AccessToken accessToken) {
        getOAuth().setAccessToken(accessToken);
    }

    @Override public AccessToken getAccessToken() throws WechatException{
        return getOAuth().getAccessToken();
    }

    private final OAuthSupport getOAuth(){
        if (!(auth instanceof OAuthSupport)){
            throw new IllegalStateException("OAuth app id/secret combination not supplied");
        }
        return (OAuthSupport)auth;
    }

    protected final void ensureAuthorizationEnabled() throws WechatException{
        if (!auth.isEnabled()){
            throw new WechatException("Ensure authorization enabled failed");
        }
    }

    @Override public final Authorization getAuthorization(){
        return this.auth;
    }

    @Override public final Configuration getConfiguration(){
        return this.conf;
    }
}
