package com.wisedu.tShow.tools.wechat.entity.message.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.tools.wechat.utils.XStreamCDATA;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestEventSubscribe extends BaseEvent{
    // 开发者微信号
    @XStreamCDATA
    @XStreamAlias("ToUserName")
    private String ToUserName;

    // 发送方帐号
    @XStreamCDATA
    @XStreamAlias("FromUserName")
    private String FromUserName;

    // 消息创建时间
    @XStreamAlias("CreateTime")
    private Integer CreateTime;

    // 消息类型
    @XStreamCDATA
    @XStreamAlias("MsgType")
    private String MsgType;

    // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    @XStreamCDATA
    @XStreamAlias("Event")
    private String Event;

    // 事件KEY值，qrscene_为前缀，后面为二维码的参数值
    @XStreamCDATA
    @XStreamAlias("EventKey")
    private String EventKey;

    @XStreamCDATA
    @XStreamAlias("Ticket")
    private String Ticket;

    public RequestEventSubscribe() {

    }

    public RequestEventSubscribe(String toUserName, String fromUserName, Integer createTime, String msgType, String event, String eventKey, String ticket) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Event = event;
        EventKey = eventKey;
        Ticket = ticket;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Integer getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Integer createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
