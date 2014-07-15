package com.wisedu.tShow.tools.wechat.handler;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.entity.message.event.RequestEventClick;
import com.wisedu.tShow.tools.wechat.entity.message.event.RequestEventSubscribe;
import com.wisedu.tShow.tools.wechat.entity.message.request.RequestImage;
import com.wisedu.tShow.tools.wechat.entity.message.request.RequestText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-15
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public class HandlerManager {
    private final static Logger log = LoggerFactory.getLogger(HandlerManager.class);

    private static Map<Class<? extends BaseMessage>, Handler> msgHandlers;

    public HandlerManager() {
        msgHandlers = new HashMap<Class<? extends BaseMessage>, Handler>();
        register(RequestText.class, new TextHandler());
        register(RequestImage.class, new ImageHandler());
        register(RequestEventClick.class, new EventClickHandler());
        register(RequestEventSubscribe.class, new EventSubscribe());
    }

    public void register(Class<? extends BaseMessage> clazz, Handler handler){
        msgHandlers.put(clazz, handler);
    }

    public BaseMessage dispatch(BaseMessage msg){
        Handler handler = msgHandlers.get(msg.getClass());
        if (handler == null){
            return null;
        }

        return handler.handle(msg);
    }
}