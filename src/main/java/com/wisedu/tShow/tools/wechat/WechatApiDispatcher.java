package com.wisedu.tShow.tools.wechat;

import com.wisedu.tShow.app.wechat.bo.message.BaseMessage;
import com.wisedu.tShow.app.wechat.bo.message.event.RequestEventSubscribe;
import com.wisedu.tShow.app.wechat.bo.message.request.RequestText;
import com.wisedu.tShow.tools.wechat.types.EventType;
import com.wisedu.tShow.tools.wechat.types.RequestMsgType;
import com.wisedu.tShow.tools.wechat.utils.EntityUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @param msg
     */
    public BaseMessage excute(String msg) throws DocumentException{
        // 根节点
        Document doc = new SAXReader().read(msg);
        Element root = doc.getRootElement();

        // 返回消息
        BaseMessage requestMsg = null;
       try {
           //消息类型
           String msgType = root.elementText("MsgType");
           RequestMsgType requestMsgType = RequestMsgType.valueOf(msg);
           switch (requestMsgType){
               case text:
                   requestMsg = new RequestText();
                   break;
               case location:
                   break;
               case image:
                   break;
               case voice:
                   break;
               case video:
                   break;
               case link:
                   break;
               case event:
                   //判断Event类型
                   EventType eventType = EventType.valueOf(
                           root.elementText("Event")
                   );
                   switch (eventType){
                       case location:  //地理位置
                           break;
                       case subscribe: //订阅（关注）
                           requestMsg = new RequestEventSubscribe();
                           break;
                       case unsubscribe:   //取消订阅（关注）
                           break;
                       case click: //菜单点击
                           break;
                       case scan:  //二维码扫描
                           break;
                       case view:  //URL跳转
                           break;
                       default:
                           break;
                   }
                   break;
               default:
                   throw new IllegalArgumentException("MsgType：" + msgType + " Unknown");
           }
           // 填充消息
           EntityUtil.FillEntityWithXml(requestMsg, root);
       } catch (IllegalArgumentException ilgamte){
           throw new IllegalArgumentException("RequestMessage转换出错！可能是MsgType不存在！，XML：" + doc.asXML(), ilgamte);
       }

        return requestMsg;
    }
}
