package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.auth.Authorization;
import com.wisedu.wechat4j.conf.HttpClientConfiguration;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public abstract class HttpClientBase implements HttpClient, Serializable {
    private static final long serialVersionUID = -2108399220104235271L;

    protected final HttpClientConfiguration conf;
    private final Map<String, String> requestHeaders;

    public HttpClientBase(HttpClientConfiguration conf){
        this.conf = conf;
        this.requestHeaders = conf.getRequestHeaders();
    }

    abstract HttpResponse handleRequest(HttpRequest request) throws IOException;

    @Override public HttpResponse request(HttpRequest request) throws IOException{
        return handleRequest(request);
    }

    @Override public HttpResponse get(String url) throws IOException{
        return request(new HttpRequest(RequestMethod.GET, url, null, null, requestHeaders));
    }

    @Override public HttpResponse get(String url, HttpParameter[] params, Authorization authorization) throws IOException{
        return request(new HttpRequest(RequestMethod.GET, url, params, authorization, requestHeaders));
    }

    @Override public HttpResponse post(String url) throws IOException{
        return request(new HttpRequest(RequestMethod.POST, url, null, null, requestHeaders));
    }

    @Override public HttpResponse post(String url, HttpParameter[] params, Authorization authorization) throws IOException{
        return request(new HttpRequest(RequestMethod.POST, url, params, authorization, requestHeaders));
    }

    @Override public HttpResponse delete(String url) throws IOException{
        return request(new HttpRequest(RequestMethod.DELETE, url, null, null, requestHeaders));
    }

    @Override public HttpResponse delete(String url, HttpParameter[] params, Authorization authorization) throws IOException{
        return request(new HttpRequest(RequestMethod.DELETE, url, params, authorization, requestHeaders));
    }

    @Override public HttpResponse put(String url) throws IOException{
        return request(new HttpRequest(RequestMethod.PUT, url, null, null, requestHeaders));
    }

    @Override public HttpResponse put(String url, HttpParameter[] params, Authorization authorization) throws IOException{
        return request(new HttpRequest(RequestMethod.PUT, url, params, authorization, requestHeaders));
    }
}
