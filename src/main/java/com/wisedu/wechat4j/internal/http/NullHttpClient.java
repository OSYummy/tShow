package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.auth.Authorization;

import java.io.IOException;
import java.io.Serializable;

public class NullHttpClient implements HttpClient, Serializable {
    private static final long serialVersionUID = -1869759854739803847L;

    private static HttpClient INSTANCE;

    private NullHttpClient(){}

    public static HttpClient getInstance() {
        if (INSTANCE == null){
            INSTANCE = new NullHttpClient();
        }
        return INSTANCE;
    }

    @Override public HttpResponse request(HttpRequest request) throws IOException {
        return null;
    }

    @Override public HttpResponse get(String url) throws IOException {
        return null;
    }

    @Override public HttpResponse get(String url, HttpParameter[] params, Authorization authorization) throws IOException {
        return null;
    }

    @Override public HttpResponse post(String url) throws IOException {
        return null;
    }

    @Override public HttpResponse post(String url, HttpParameter[] params, Authorization authorization) throws IOException {
        return null;
    }

    @Override public HttpResponse delete(String url) throws IOException {
        return null;
    }

    @Override public HttpResponse delete(String url, HttpParameter[] params, Authorization authorization) throws IOException {
        return null;
    }

    @Override public HttpResponse put(String url) throws IOException {
        return null;
    }

    @Override public HttpResponse put(String url, HttpParameter[] params, Authorization authorization) throws IOException {
        return null;
    }
}
