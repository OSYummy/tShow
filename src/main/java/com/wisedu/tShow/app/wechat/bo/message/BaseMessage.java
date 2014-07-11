package com.wisedu.tShow.app.wechat.bo.message;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMessage {
    // 开发者微信号
    private String ToUserName;

    // 发送方帐号
    private String FromUserName;

    // 消息创建时间
    private Integer CreateTime;

    // 消息类型
    private String MsgType;

    public abstract String asXML();

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
}
