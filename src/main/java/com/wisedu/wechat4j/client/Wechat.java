package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.api.WechatSupport;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.conf.ConfigurationContext;

public abstract class Wechat implements WechatSupport{
    private static final Configuration DEFAULT_CONF =ConfigurationContext.getInstance();
    private static final License NULL_LICENSE = new License(null, null, null);

    public static Wechat newInstance(){
        return Wechat.newInstance(DEFAULT_CONF, NULL_LICENSE);
    }

    public static Wechat newInstance(License license) {
        return Wechat.newInstance(DEFAULT_CONF, license);
    }

    public static Wechat newInstance(Configuration conf, License license) {
        return new WechatImpl(conf, license);
    }
}
