package com.wisedu.wechat4j.entity;

import java.util.List;

public interface Button{
    ButtonType getButtonType();

    String getName();

    String getKey();

    String getURL();

    List<Button> getSubButton();
}
