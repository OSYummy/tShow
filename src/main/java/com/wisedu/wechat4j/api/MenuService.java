package com.wisedu.wechat4j.api;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.entity.Button;
import com.wisedu.wechat4j.entity.Menu;

import java.util.List;

public interface MenuService {
    Menu getMenu() throws WechatException;

    public void createMenu(Menu menu) throws WechatException;

    public void deleteMenu() throws WechatException;
}
