package com.wisedu.wechat4j.api;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.entity.Button;
import com.wisedu.wechat4j.entity.Menu;
import com.wisedu.wechat4j.entity.Response;
import com.wisedu.wechat4j.internal.json.JSONObject;

import java.util.List;

public interface MenuService {
    Menu getMenu() throws WechatException;

    public Response createMenu(Menu menu) throws WechatException;

    public Response createMenu(JSONObject jsonObject) throws WechatException;

    public Response deleteMenu() throws WechatException;
}
