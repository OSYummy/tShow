package com.wisedu.wechat4j.api;

import com.wisedu.wechat4j.auth.OAuthSupport;

import java.io.Serializable;

public interface Wechat extends Serializable,
        OAuthSupport,
        MenuService,
        GroupService{
}
