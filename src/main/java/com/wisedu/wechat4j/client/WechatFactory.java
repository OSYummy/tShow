package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.api.Wechat;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.conf.ConfigurationContext;
import com.wisedu.wechat4j.internal.util.W4JLRUCache;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class WechatFactory implements Serializable {
    private static final long serialVersionUID = 690910694740944346L;

    private static final Configuration conf;
    private static final Constructor<Wechat> WECHAT_CONSTRUCTOR;
    private static final W4JLRUCache<License, Wechat> instanceMap = new W4JLRUCache<License, Wechat>(20);

    static {
        conf = ConfigurationContext.getInstance();

        Constructor<Wechat> constructor = null;
        String className = "com.wisedu.wechat4j.client.WechatImpl";
        try {
            Class clazz = Class.forName(className);
            constructor = clazz.getDeclaredConstructor(Configuration.class, License.class);
        } catch (ClassNotFoundException cnfe){
            throw new AssertionError(cnfe);
        } catch (NoSuchMethodException nsme){
            throw new AssertionError(nsme);
        }
        WECHAT_CONSTRUCTOR = constructor;
    }

    private WechatFactory(){}

    public static Wechat getInstance(){
        return null;
    }

    public static Wechat getInstance(License license){
        return createClient(license, conf);
    }

    private static Wechat createClient(License license, Configuration conf){
        Wechat client = null;
        try {
            client = instanceMap.get(license);
            if (client == null){
                client = WECHAT_CONSTRUCTOR.newInstance(conf, license);
                instanceMap.put(license, client);
            }
        } catch (InstantiationException ie){
            throw new AssertionError(ie);
        } catch (IllegalAccessException iae){
            throw new AssertionError(iae);
        } catch (InvocationTargetException ite){
            throw new AssertionError(ite);
        }
        return client;
    }
}
