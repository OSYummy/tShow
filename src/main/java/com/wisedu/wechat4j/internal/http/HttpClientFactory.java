package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.conf.ConfigurationContext;
import com.wisedu.wechat4j.conf.HttpClientConfiguration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class HttpClientFactory {
    private static final Constructor<HttpClient> HTTP_CLIENT_CONSTRUCTOR;
    private static final String HTTP_CLIENT_IMPLEMENTATION = "wechat4j.http.httpClient";

    static {
        Class<?> clazz = null;
        String httpClientImpl = System.getProperty(HTTP_CLIENT_IMPLEMENTATION);
        if (httpClientImpl != null){
            try {
                clazz = Class.forName(httpClientImpl);
            } catch (ClassNotFoundException cnfe){
                throw new AssertionError(cnfe);
            }
        }

        if (null == clazz){
            try {
                clazz = Class.forName("com.wisedu.wechat4j.internal.http.HttpClientImpl");
            } catch (ClassNotFoundException cnfe){
                throw new AssertionError(cnfe);
            }
        }

        try {
            HTTP_CLIENT_CONSTRUCTOR = (Constructor<HttpClient>)clazz.getDeclaredConstructor(HttpClientConfiguration.class);
        } catch (NoSuchMethodException nsme){
            throw new AssertionError(nsme);
        }
    }

    public static HttpClient getInstance(){
        return getInstance(ConfigurationContext.getInstance());
    }

    public static HttpClient getInstance(HttpClientConfiguration conf){
        HttpClient client = null;
        try {
            client = HTTP_CLIENT_CONSTRUCTOR.newInstance(conf);
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
