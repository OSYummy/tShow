package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.api.Wechat;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.entity.Button;
import com.wisedu.wechat4j.internal.http.HttpParameter;
import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.util.List;

class WechatImpl extends WechatBaseImpl implements Wechat {
    WechatImpl(Configuration conf, License license){
        super(conf, license);
    }

    private HttpResponse get(String url, HttpParameter[] params) throws WechatException {
        HttpResponse response = null;
        for (int i=0; i<conf.getHttpRetryCount(); i++){
            ensureAuthorizationEnabled();
            try {
                return http.get(url, params, auth);
            } catch (IOException ioe){
                throw new WechatException("Http Get failed", ioe);
            }
        }
        return response;
    }

    private HttpResponse post(String url, HttpParameter[] params) throws WechatException{
        HttpResponse response = null;
        for (int i=0; i<conf.getHttpRetryCount(); i++){
            ensureAuthorizationEnabled();
            try {
                response = http.post(url, params, auth);
            } catch (IOException ioe){
                throw new WechatException("Http Post failed", ioe);
            }
        }
        return response;
    }

    @Override public List<Button> listButtons() throws WechatException{
        return factory.createButtonList(get(conf.getRestBaseURL() + "menu/get", null));
    }

    public void createButtons(List<Button> buttons) throws WechatException{
        String url = conf.getRestBaseURL() + "menu/create";
        HttpParameter[] params = new HttpParameter[]{
                new HttpParameter(new JSONObject())
        };
        post(url, params);
    }

    public void deleteButtons() throws WechatException{
        get(conf.getRestBaseURL() + "menu/delete", null);
    }
}