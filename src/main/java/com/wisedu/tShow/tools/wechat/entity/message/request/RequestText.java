package com.wisedu.tShow.tools.wechat.entity.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestText extends BaseMessage {
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

    // 文本消息内容
    @XStreamAlias("Content")
    private String Content;

    // 消息id，64位整型
    @XStreamAlias("MsgId")
    private Long MsgId;

    public RequestText() {

    }
}
