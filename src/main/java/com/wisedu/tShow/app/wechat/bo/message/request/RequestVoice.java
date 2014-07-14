package com.wisedu.tShow.app.wechat.bo.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.app.wechat.bo.message.BaseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestVoice extends BaseMessage{
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

    // 语音消息媒体id
    @XStreamAlias("MediaId")
    private String MediaId;

    // 语音格式
    @XStreamAlias("Format")
    private String Format;

    // 消息id
    @XStreamAlias("MsgId")
    private Long MsgId;

    @XStreamAlias("Recognition")
    private String Recognition;

    public RequestVoice() {

    }
}
