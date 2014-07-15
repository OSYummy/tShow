package com.wisedu.tShow.tools.wechat.entity.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.utils.XStreamCDATA;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestVideo extends BaseMessage{
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

    // 视频消息媒体id
    @XStreamCDATA
    @XStreamAlias("MediaId")
    private String MediaId;

    // 视频消息缩略图的媒体id
    @XStreamCDATA
    @XStreamAlias("ThumbMediaId")
    private String ThumbMediaId;

    // 消息id，64位整型
    @XStreamAlias("MsgId")
    private Long MsgId;

    public RequestVideo() {
    }
}
