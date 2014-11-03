package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.internal.http.HttpParameter;

import java.io.Serializable;

public final class NullAuthorization implements Authorization, Serializable {
    private static final long serialVersionUID = 7638666871608411293L;

    private static NullAuthorization INSTANCE;

    private NullAuthorization(){ }

    public static Authorization getInstance(){
        if (INSTANCE == null){
            INSTANCE = new NullAuthorization();
        }
        return INSTANCE;
    }

    @Override public HttpParameter[] generateAuthorizationParameter() {
        return new HttpParameter[]{};
    }

    @Override public boolean isEnabled() {
        return false;
    }
}
