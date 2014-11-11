package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.internal.http.HttpParameter;

import java.io.Serializable;

public final class OAuth2Authorization implements Authorization, OAuth2Support, Serializable {
    private static final long serialVersionUID = 6970138185096298628L;

    @Override public HttpParameter[] generateAuthorizationParameter() {
        return new HttpParameter[]{};
    }

    @Override public boolean isEnabled() throws WechatException {
        return false;
    }
}
