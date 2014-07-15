package com.wisedu.tShow.tools.wechat.entity.message.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.tools.wechat.utils.XStreamCDATA;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestEventScan extends BaseEvent{
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

    // 事件类型，SCAN
    @XStreamCDATA
    @XStreamAlias("Event")
    private String Event;

    // 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    @XStreamCDATA
    @XStreamAlias("EventKey")
    private Integer EventKey;

    // 二维码的ticket，可用来换取二维码图片
    @XStreamCDATA
    @XStreamAlias("Ticket")
    private String Ticket;

    public RequestEventScan() {

    }
}
