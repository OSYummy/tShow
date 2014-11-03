package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.auth.Authorization;

import java.io.IOException;

public interface HttpClient {
    HttpResponse request(HttpRequest request) throws IOException;

    HttpResponse get(String url) throws IOException;

    HttpResponse get(String url, HttpParameter[] params, Authorization authorization) throws IOException;

    HttpResponse post(String url) throws IOException;

    HttpResponse post(String url, HttpParameter[] params, Authorization authorization) throws IOException;

    HttpResponse delete(String url) throws IOException;

    HttpResponse delete(String url, HttpParameter[] params, Authorization authorization) throws IOException;

    HttpResponse put(String url) throws IOException;

    HttpResponse put(String url, HttpParameter[] params, Authorization authorization) throws IOException;
}
