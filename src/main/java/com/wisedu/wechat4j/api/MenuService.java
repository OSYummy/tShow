package com.wisedu.wechat4j.api;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.entity.Button;

import java.util.List;

public interface MenuService {
    List<Button> listButtons() throws WechatException;

    public void createButtons(List<Button> buttons) throws WechatException;

    public void deleteButtons() throws WechatException;
}
