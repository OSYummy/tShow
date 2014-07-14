package com.wisedu.tShow.app.wechat.bo.message.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestEventView extends BaseEvent {
    // 开发者微信号
    @XStreamAlias("ToUserName")
    private String ToUserName;

    // 发送方帐号
    @XStreamAlias("FromUserName")
    private String FromUserName;

    // 消息创建时间
    @XStreamAlias("CreateTime")
    private Integer CreateTime;

    // 消息类型
    @XStreamAlias("MsgType")
    private String MsgType;

    // 事件类型，VIEW
    @XStreamAlias("Event")
    private String Event;

    // 事件KEY值，设置的跳转URL
    @XStreamAlias("EventKey")
    private String EventKey;

    public RequestEventView() {

    }
}
