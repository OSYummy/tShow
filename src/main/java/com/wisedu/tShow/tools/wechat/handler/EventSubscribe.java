package com.wisedu.tShow.tools.wechat.handler;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.entity.message.event.RequestEventSubscribe;
import com.wisedu.tShow.tools.wechat.entity.message.response.ResponseText;
import com.wisedu.tShow.tools.wechat.types.ResponseMsgType;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-15
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
public class EventSubscribe implements Handler{
    @Override
    public BaseMessage handle(BaseMessage msg) {
        if (!(msg instanceof RequestEventSubscribe)){
            return null;
        }

        RequestEventSubscribe request = (RequestEventSubscribe)msg;

        String content = "谢谢关注/::T";

        ResponseText response = new ResponseText(
                request.getFromUserName(), request.getToUserName(),
                (int)System.currentTimeMillis(), ResponseMsgType.text.toString(), content
        );
        return response;
    }
}
