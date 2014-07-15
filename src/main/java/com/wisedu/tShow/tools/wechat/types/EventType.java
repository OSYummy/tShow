package com.wisedu.tShow.tools.wechat.types;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-13
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
public enum EventType {
    // 订阅
    subscribe,

    // 取消订阅
    unsubscribe,

    // 二维码扫描
    SCAN,

    // 地理位置
    LOCATION,

    // 自定义菜单点击事件
    CLICK,

    // URL跳转
    VIEW
}
