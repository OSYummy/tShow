package com.wisedu.tShow.tools.wechat;

import com.wisedu.tShow.tools.wechat.entity.message.response.Image;
import com.wisedu.tShow.tools.wechat.entity.message.response.ResponseImage;
import com.wisedu.tShow.tools.wechat.entity.message.response.ResponseText;
import com.wisedu.tShow.tools.wechat.handler.HandlerManager;
import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.entity.message.event.*;
import com.wisedu.tShow.tools.wechat.entity.message.request.*;
import com.wisedu.tShow.tools.wechat.types.EventType;
import com.wisedu.tShow.tools.wechat.types.RequestMsgType;
import com.wisedu.tShow.tools.wechat.utils.XStreamUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-11
 * Time: 下午1:38
 * To change this template use File | Settings | File Templates.
 */
public class WechatApiDispatcher {
    private final static Logger log = LoggerFactory.getLogger(WechatApiDispatcher.class);

    public static HandlerManager handlerManager;

    private static DocumentFactory docFactory;

    public WechatApiDispatcher() {
        handlerManager = new HandlerManager();
        docFactory = DocumentFactory.getInstance();
    }

    /**
     * 处理消息
     * @param msg XML消息
     */
    public BaseMessage excute(String msg) throws DocumentException{
        // 根节点
        Document doc = new SAXReader().read(new StringReader(msg));
        Element root = doc.getRootElement();

        // 返回消息
        BaseMessage request = null;
        BaseMessage response = null;
        try {
            //消息类型
            String msgType = root.elementText("MsgType");
            RequestMsgType requestMsgType = RequestMsgType.valueOf(msgType);
            switch (requestMsgType){
                case text:
                    request = new RequestText();
                    break;
                case location:
                    request = new RequestLocation();
                    break;
                case image:
                    request = new RequestImage();
                    break;
                case voice:
                    request = new RequestVoice();
                    break;
                case video:
                    request = new RequestVideo();
                    break;
                case link:
                    request = new RequestLink();
                    break;
                case event:
                    //判断Event类型
                    EventType eventType = EventType.valueOf(
                            root.elementText("Event")
                    );
                    switch (eventType){
                        case LOCATION:  //地理位置
                            request = new RequestEventLocation();
                            break;
                        case subscribe: //订阅（关注）
                            request = new RequestEventSubscribe();
                            break;
                        case unsubscribe:   //取消订阅（关注）
                            request = new RequestEventUnSubscribe();
                            break;
                        case CLICK: //菜单点击
                            request = new RequestEventClick();
                            break;
                        case SCAN:  //二维码扫描
                            request = new RequestEventScan();
                            break;
                        case VIEW:  //URL跳转
                            request = new RequestEventView();
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("MsgType：" + msgType + " Unknown");
            }
        } catch (IllegalArgumentException ilgamte){
            log.error(ilgamte.getMessage());
            throw new IllegalArgumentException("message convert error, xml: " + doc.asXML(), ilgamte);
        }

        if (request == null){
            return null;
        }

        // 填充消息
        request = XStreamUtil.fromXml(doc.asXML(), request);

        // 处理消息
        response = handlerManager.dispatch(request);

        return response;
    }
}
