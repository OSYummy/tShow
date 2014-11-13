package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.api.WechatSupport;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.conf.ConfigurationContext;

public abstract class Wechat implements WechatSupport{
    private static final Configuration DEFAULT_CONF =ConfigurationContext.getInstance();

    public static Wechat newInstance(){
        return Wechat.newInstance(DEFAULT_CONF, License.getSingleton());
    }

    public static Wechat newInstance(License license) {
        return Wechat.newInstance(DEFAULT_CONF, license);
    }

    public static Wechat newInstance(Configuration conf, License license) {
        return new WechatImpl(conf, license);
    }
}
