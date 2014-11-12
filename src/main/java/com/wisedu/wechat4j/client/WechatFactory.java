package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.conf.ConfigurationContext;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class WechatFactory implements Serializable {
    private static final long serialVersionUID = 3863069838898599852L;

    private static final Configuration conf;
    private static final Constructor<Wechat> WECHAT_CONSTRUCTOR;

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

    public static Wechat create() throws WechatException{
        Wechat client = null;
        try {
            client = WECHAT_CONSTRUCTOR.newInstance(conf);
        } catch (InstantiationException ie){
            throw new WechatException(ie);
        } catch (IllegalAccessException iae){
            throw new WechatException(iae);
        } catch (InvocationTargetException ite){
            throw new WechatException(ite);
        }
        return client;
    }
}
