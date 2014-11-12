package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.conf.ConfigurationContext;
import com.wisedu.wechat4j.conf.HttpClientConfiguration;
import com.wisedu.wechat4j.internal.logger.Logger;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class HttpClientFactory implements Serializable {
    private static final long serialVersionUID = -5854084715172501672L;

    private static final Logger log  = Logger.getLogger(HttpClientFactory.class);

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
            log.error(ie.getMessage());
        } catch (IllegalAccessException iae){
            log.error(iae.getMessage());
        } catch (InvocationTargetException ite){
            log.error(ite.getMessage());
        }

        if (client == null){
            client = NullHttpClient.getInstance();
        }
        return client;
    }
}
