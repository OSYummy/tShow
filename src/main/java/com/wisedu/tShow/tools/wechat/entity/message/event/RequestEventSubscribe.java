package com.wisedu.tShow.tools.wechat.entity.message.event;

import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ToUserName", "FromUserName", "CreateTime", "MsgType", "Event", "EventKey", "Ticket"})
public class RequestEventSubscribe extends BaseEvent{
    // 开发者微信号
    @XmlElement(name = "ToUserName", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String ToUserName;

    // 发送方帐号
    @XmlElement(name = "FromUserName", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String FromUserName;

    // 消息创建时间
    @XmlElement(name = "CreateTime", required = true)
    private Integer CreateTime;

    // 消息类型,event
    @XmlElement(name = "MsgType", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String MsgType;

    // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    @XmlElement(name = "Event", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Event;

    // 事件KEY值，qrscene_为前缀，后面为二维码的参数值
    @XmlElement(name = "EventKey", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String EventKey;

    // 二维码的ticket，可用来换取二维码图片
    @XmlElement(name = "Ticket", required = false)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
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
