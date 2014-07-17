package com.wisedu.tShow.tools.wechat.entity.message.event;

import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ToUserName", "FromUserName", "CreateTime", "MsgType", "Event", "Latitude", "Longitude", "Precision"})
public class RequestEventLocation extends BaseEvent{
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

    // 事件类型，LOCATION
    @XmlElement(name = "Event", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String Event;

    // 地理位置纬度
    @XmlElement(name = "Latitude", required = true)
    private Double Latitude;

    // 地理位置经度
    @XmlElement(name = "Longitude", required = true)
    private Double Longitude;

    // 地理位置精度
    @XmlElement(name = "Precision", required = true)
    private Double Precision;

    public RequestEventLocation() {

    }

    public RequestEventLocation(String toUserName, String fromUserName, Integer createTime, String msgType, String event, Double latitude, Double longitude, Double precision) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Event = event;
        Latitude = latitude;
        Longitude = longitude;
        Precision = precision;
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

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getPrecision() {
        return Precision;
    }

    public void setPrecision(Double precision) {
        Precision = precision;
    }
}
