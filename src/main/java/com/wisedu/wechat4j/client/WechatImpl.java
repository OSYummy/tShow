package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.entity.Button;
import com.wisedu.wechat4j.entity.Contact;
import com.wisedu.wechat4j.entity.Menu;
import com.wisedu.wechat4j.entity.Response;
import com.wisedu.wechat4j.internal.http.HttpParameter;
import com.wisedu.wechat4j.internal.http.HttpResponse;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

class WechatImpl extends WechatBaseImpl implements Serializable {
    private static final long serialVersionUID = -1192031969535006001L;

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

    @Override public Menu getMenu() throws WechatException{
        return factory.createMenu(get(conf.getRestBaseURL() + "menu/get", null));
    }

    public Response createMenu(Menu menu) throws WechatException{
        String url = conf.getRestBaseURL() + "menu/create";
        HttpParameter[] params = new HttpParameter[]{
                new HttpParameter(new JSONObject())
        };
        return factory.createResponse(post(url, params));
    }

    public Response createMenu(JSONObject jsonObject) throws WechatException{
        String url = conf.getRestBaseURL() + "menu/create";
        HttpParameter[] params = new HttpParameter[] {
                new HttpParameter(jsonObject)
        };
        return factory.createResponse(post(url, params));
    }

    public Response deleteMenu() throws WechatException{
        return factory.createResponse(get(conf.getRestBaseURL() + "menu/delete", null));
    }

    public Contact getContact() throws WechatException{
        return factory.createContact(get(conf.getRestBaseURL() + "groups/get", null));
    }
}