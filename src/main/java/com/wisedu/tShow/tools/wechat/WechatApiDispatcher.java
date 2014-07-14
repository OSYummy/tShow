package com.wisedu.tShow.tools.wechat;

import com.thoughtworks.xstream.XStream;
import com.wisedu.tShow.app.wechat.bo.message.BaseMessage;
import com.wisedu.tShow.app.wechat.bo.message.event.*;
import com.wisedu.tShow.app.wechat.bo.message.request.*;
import com.wisedu.tShow.tools.wechat.types.EventType;
import com.wisedu.tShow.tools.wechat.types.RequestMsgType;
import com.wisedu.tShow.tools.wechat.utils.EntityUtil;
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

    private static DocumentFactory docFactory = DocumentFactory.getInstance();

    /**
     * 处理消息
     * @param msg XML消息
     */
    public BaseMessage excute(String msg) throws DocumentException{
        // 根节点
        Document doc = new SAXReader().read(new StringReader(msg));
        Element root = doc.getRootElement();

        // 返回消息
        BaseMessage requestMsg = null;
        try {
            //消息类型
            String msgType = root.elementText("MsgType");
            RequestMsgType requestMsgType = RequestMsgType.valueOf(msgType);
            switch (requestMsgType){
                case text:
                    requestMsg = new RequestText();
                    break;
                case location:
                    requestMsg = new RequestLocation();
                    break;
                case image:
                    requestMsg = new RequestImage();
                    break;
                case voice:
                    requestMsg = new RequestVoice();
                    break;
                case video:
                    requestMsg = new RequestVideo();
                    break;
                case link:
                    requestMsg = new RequestLink();
                    break;
                case event:
                    //判断Event类型
                    EventType eventType = EventType.valueOf(
                            root.elementText("Event")
                    );
                    switch (eventType){
                        case location:  //地理位置
                            requestMsg = new RequestEventLocation();
                            break;
                        case subscribe: //订阅（关注）
                            requestMsg = new RequestEventSubscribe();
                            break;
                        case unsubscribe:   //取消订阅（关注）
                            requestMsg = new RequestEventUnSubscribe();
                            break;
                        case click: //菜单点击
                            requestMsg = new RequestEventClick();
                            break;
                        case scan:  //二维码扫描
                            requestMsg = new RequestEventScan();
                            break;
                        case view:  //URL跳转
                            requestMsg = new RequestEventView();
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

        if (requestMsg == null){
            return null;
        }

        // 填充消息
        requestMsg = XStreamUtil.fromXml(doc.asXML(), requestMsg);

        return requestMsg;
    }
}
