package com.wisedu.wechat4j.auth;

import java.io.Serializable;

abstract class OAuthToken implements Serializable {
    private static final long serialVersionUID = -8499784385025624845L;

    private final String token;

    public OAuthToken(String token){
        this.token = token;
    }
}
