package com.wisedu.tShow.tools.wechat.handler;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.entity.message.request.RequestText;
import com.wisedu.tShow.tools.wechat.entity.message.response.ResponseText;
import com.wisedu.tShow.tools.wechat.types.RequestMsgType;
import com.wisedu.tShow.tools.wechat.types.ResponseMsgType;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-15
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class TextHandler implements Handler {
    @Override
    public BaseMessage handle(BaseMessage msg) {
        if (!(msg instanceof RequestText)){
            return null;
        }

        // 请求消息
        RequestText request = (RequestText)msg;

        // 回应消息
        ResponseText responseText = new ResponseText(
                request.getFromUserName(), request.getToUserName(),
                (int)System.currentTimeMillis(), ResponseMsgType.text.toString(), request.getContent()
        );
        return responseText;
    }
}
