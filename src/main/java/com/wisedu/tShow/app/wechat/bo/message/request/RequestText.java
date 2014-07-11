package com.wisedu.tShow.app.wechat.bo.message.request;

import com.wisedu.tShow.app.wechat.bo.message.BaseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class RequestText extends BaseMessage {
    private String Content;

    private Integer MsgId;

    public RequestText() {
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getMsgId() {
        return MsgId;
    }

    public void setMsgId(Integer msgId) {
        MsgId = msgId;
    }

    @Override
    public String asXML() {
        return null;
    }
}
