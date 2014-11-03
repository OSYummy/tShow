package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.conf.HttpClientConfiguration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class HttpResponseImpl extends HttpResponse{
    private HttpURLConnection con;

    public HttpResponseImpl(HttpURLConnection con, HttpClientConfiguration conf) throws IOException {
        super(conf);
        this.con = con;

        this.statusCode = con.getResponseCode();

        if (null == con.getErrorStream()){
            is = con.getInputStream();
        }

        if (is!=null && "gzip".equals(con.getContentType())){
            is = new GZIPInputStream(is);
        }
    }

    public HttpResponseImpl(String content){
        super();
        this.responseAsString = content;
    }

    @Override public String getResponseHeader(String name) {
        return con.getHeaderField(name);
    }

    @Override public Map<String, List<String>> getResponseHeaderFields() {
        return con.getHeaderFields();
    }

    @Override public void disconnect(){
        con.disconnect();
    }
}
