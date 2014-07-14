package com.wisedu.tShow.tools.wechat.entity.message.response;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class ResponseText extends BaseMessage {
    private String Content;

    public ResponseText() {
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String asXML() {
        return null;
    }
}
