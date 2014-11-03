package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.api.Wechat;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.entity.Button;
import com.wisedu.wechat4j.internal.http.HttpParameter;
import com.wisedu.wechat4j.internal.http.HttpResponse;

import java.io.IOException;
import java.util.List;

class WechatImpl extends WechatBaseImpl implements Wechat {
    WechatImpl(Configuration conf, License license){
        super(conf, license);
    }

    private HttpResponse get(String url, HttpParameter[] params) throws WechatException {
        ensureAuthorizationEnabled();
        try {
            return http.get(url, params, auth);
        } catch (IOException ioe){
            throw new WechatException("Http Get failed", ioe);
        }
    }

    private HttpResponse post(String url, HttpParameter[] params) throws WechatException{
        ensureAuthorizationEnabled();
        try {
            return http.post(url, params, auth);
        } catch (IOException ioe){
            throw new WechatException("Http Post failed", ioe);
        }
    }

    @Override public List<Button> listButtons() throws WechatException{
        ensureAuthorizationEnabled();
        return factory.createButtonList(get(conf.getRestBaseURL() + "menu/get", null));
    }

    public void createButtons(List<Button> buttons) throws WechatException{
        ensureAuthorizationEnabled();
        String url = conf.getRestBaseURL() + "menu/create";
        HttpParameter[] params = new HttpParameter[]{
                new HttpParameter("", "")
        };
        post(url, params);
    }

    public void deleteButtons() throws WechatException{
        ensureAuthorizationEnabled();
        get(conf.getRestBaseURL() + "menu/delete", null);
    }
}