package com.wisedu.tShow.tools.wechat.action;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public interface WechatAction {
    public BaseMessage handle(BaseMessage msg);
}
