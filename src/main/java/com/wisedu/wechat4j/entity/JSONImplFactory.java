package com.wisedu.wechat4j.entity;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.internal.http.HttpResponse;

import java.io.IOException;
import java.util.List;

public class JSONImplFactory implements ObjectFactory{

    @Override public Response createResponse(HttpResponse response) throws WechatException {
        try {
            return ResponseJSONImpl.createResponse(response);
        } catch (IOException ioe) {
            throw new WechatException("Create Response Failed.", ioe);
        }
    }

    @Override public Menu createMenu(HttpResponse response) throws WechatException{
        try {
            return MenuJSONImpl.createMenu(response);
        } catch (IOException ioe) {
            throw new WechatException("Create Menu Failed.", ioe);
        }
    }

    @Override public Contact createContact(HttpResponse response) throws WechatException {
        try {
            return ContactJSONImpl.createContact(response);
        } catch (IOException ioe) {
            throw new WechatException("Create Contact Failed.", ioe);
        }
    }
}