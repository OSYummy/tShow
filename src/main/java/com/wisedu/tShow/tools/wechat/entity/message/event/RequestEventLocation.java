package com.wisedu.tShow.tools.wechat.entity.message.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.tools.wechat.utils.XStreamCDATA;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestEventLocation extends BaseEvent{
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

    // 事件类型，LOCATION
    @XStreamCDATA
    @XStreamAlias("Event")
    private String Event;

    // 地理位置纬度
    @XStreamAlias("Latitude")
    private Double Latitude;

    // 地理位置经度
    @XStreamAlias("Longitude")
    private Double Longitude;

    // 地理位置精度
    @XStreamAlias("Precision")
    private Double Precision;

    public RequestEventLocation() {

    }
}
