package com.wisedu.tShow.tools.wechat.handler;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.entity.message.event.RequestEventClick;
import com.wisedu.tShow.tools.wechat.entity.message.response.ResponseText;
import com.wisedu.tShow.tools.wechat.types.ResponseMsgType;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-15
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class EventClickHandler implements Handler{
    @Override
    public BaseMessage handle(BaseMessage msg) {
        if (!(msg instanceof RequestEventClick)){
            return null;
        }

        RequestEventClick request = (RequestEventClick)msg;

        String content = "<a href=\"http://wechat.ngrok.com/tShow/home/index.do\">首页</a>";

        ResponseText response = new ResponseText(
                request.getFromUserName(), request.getToUserName(),
                (int)System.currentTimeMillis(), ResponseMsgType.text.toString(), content
        );
        return response;
    }
}
