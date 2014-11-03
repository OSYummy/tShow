package com.wisedu.wechat4j.auth;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.internal.http.HttpParameter;

import java.io.Serializable;

public interface Authorization extends Serializable {
    HttpParameter[] generateAuthorizationParameter();

    /**
     * Returns true if authorization credentials are set.
     *
     * @return true if authorization credentials are set
     */
    boolean isEnabled() throws WechatException;
}
