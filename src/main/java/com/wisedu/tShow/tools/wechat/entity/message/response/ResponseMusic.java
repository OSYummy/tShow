package com.wisedu.tShow.tools.wechat.entity.message.response;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-15
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ToUserName", "FromUserName", "CreateTime", "MsgType", "music"})
public class ResponseMusic extends BaseMessage{
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

    // 音乐消息, music
    @XmlElement(name = "Music", required = true)
    private Music music;

    public ResponseMusic() {

    }

    public ResponseMusic(String toUserName, String fromUserName, Integer createTime, String msgType, Music music) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        this.music = music;
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

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}