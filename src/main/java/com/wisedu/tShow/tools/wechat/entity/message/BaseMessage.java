package com.wisedu.tShow.tools.wechat.entity.message;

import com.wisedu.tShow.tools.wechat.utils.XStreamUtil;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMessage {
    public String asXML(){
        return XStreamUtil.toXml(this);
    }
}
