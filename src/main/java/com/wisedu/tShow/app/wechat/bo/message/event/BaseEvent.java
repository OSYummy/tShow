package com.wisedu.tShow.app.wechat.bo.message.event;

import com.wisedu.tShow.app.wechat.bo.message.BaseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseEvent extends BaseMessage{
    public String Event;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
