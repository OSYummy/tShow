package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.auth.*;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.entity.JSONImplFactory;
import com.wisedu.wechat4j.entity.ObjectFactory;
import com.wisedu.wechat4j.internal.http.HttpClient;
import com.wisedu.wechat4j.internal.http.HttpClientFactory;

import java.io.Serializable;

abstract class WechatBaseImpl implements WechatBase, OAuthSupport, Serializable {
    private static final long serialVersionUID = -361426419584261734L;

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

    @Override public void setOAuthApp(String appId, String appSecret){
        if (appId == null){
            throw new NullPointerException("app id is null");
        }
        if (appSecret == null){
            throw new NullPointerException("app secret is null");
        }

        if (auth instanceof NullAuthorization){
            OAuthAuthorization auth
                    = new OAuthAuthorization(conf, new LicenseImpl("", appId, appSecret), http);
            auth.setOAuthApp(appId, appSecret);
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
            throw new WechatException("Ensure authorization enabled fail");
        }
    }

    @Override public final Authorization getAuthorization(){
        return this.auth;
    }

    @Override public final Configuration getConfiguration(){
        return this.conf;
    }
}
