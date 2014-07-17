package com.wisedu.tShow.tools.wechat.entity.message.event;

import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ToUserName", "FromUserName", "CreateTime", "MsgType", "Event", "EventKey", "Ticket"})
public class RequestEventScan extends BaseEvent{
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

    // 消息类型
    @XmlElement(name = "MsgType", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String MsgType;

    // 事件类型，SCAN
    @XmlElement(name = "Event", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Event;

    // 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    @XmlElement(name = "EventKey", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private Integer EventKey;

    // 二维码的ticket，可用来换取二维码图片
    @XmlElement(name = "Ticket", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Ticket;

    public RequestEventScan() {

    }

    public RequestEventScan(String toUserName, String fromUserName, Integer createTime, String msgType, String event, Integer eventKey, String ticket) {
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

    public Integer getEventKey() {
        return EventKey;
    }

    public void setEventKey(Integer eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
