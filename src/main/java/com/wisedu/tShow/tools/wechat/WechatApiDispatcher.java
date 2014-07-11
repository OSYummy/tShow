package com.wisedu.tShow.tools.wechat;

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
    public String excute(String msg) throws DocumentException{
        // 根节点
        Document doc = new SAXReader().read(msg);
        Element root = doc.getRootElement();

        // 消息类型
        String msgType = root.elementText("MsgType");
        switch (1){
            case 1:
                break;
            default:
        }
        return null;
    }
}
