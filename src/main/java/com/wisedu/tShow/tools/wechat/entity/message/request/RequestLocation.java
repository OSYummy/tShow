package com.wisedu.tShow.tools.wechat.entity.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestLocation extends BaseMessage{
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

    // 地理位置纬度
    @XStreamAlias("Location_X")
    private Double Location_X;

    // 地理位置经度
    @XStreamAlias("Location_Y")
    private Double Location_Y;

    // 地图缩放大小
    @XStreamAlias("Scale")
    private Integer Scale;

    // 地理位置信息
    @XStreamAlias("Label")
    private String Label;

    // 消息id，64位整型
    @XStreamAlias("MsgId")
    private Long MsgId;

    public RequestLocation() {

    }
}
