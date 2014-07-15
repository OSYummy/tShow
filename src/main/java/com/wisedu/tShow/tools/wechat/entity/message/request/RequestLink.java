package com.wisedu.tShow.tools.wechat.entity.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.utils.XStreamCDATA;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("xml")
public class RequestLink extends BaseMessage{
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

    // 消息标题
    @XStreamCDATA
    @XStreamAlias("Title")
    private String Title;

    // 消息标题
    @XStreamCDATA
    @XStreamAlias("Description")
    private String Description;

    // 消息描述
    @XStreamCDATA
    @XStreamAlias("URL")
    private String URL;

    // 消息id，64位整型
    @XStreamAlias("MsgId")
    private Long MsgId;

    public RequestLink() {

    }
}
