package com.wisedu.tShow.app.wechat.web;

import com.wisedu.core.common.utils.PropertyConfigurerUtil;
import com.wisedu.tShow.tools.wechat.WechatApiDispatch;
import com.wisedu.tShow.utils.WechatUtil;
import org.apache.commons.io.IOUtils;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-10
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value="/wechat")
public class WechatController {
    private final static Logger log = LoggerFactory.getLogger(WechatController.class);

    private final static String token = (String)PropertyConfigurerUtil.getProperty("app.wechat.token");

    private final static String appId = (String)PropertyConfigurerUtil.getProperty("app.wechat.appId");

    private final static String appSecret = (String)PropertyConfigurerUtil.getProperty("app.wechat.appSecret");

    private static DocumentFactory docFactory = DocumentFactory.getInstance();

    private static WechatApiDispatch dispatch = new WechatApiDispatch();

    /**
     * 服务器配置验证
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wechat.do", method = RequestMethod.GET)
    public void check(HttpServletRequest request, HttpServletResponse response){
        // 加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        try {
            String digest = WechatUtil.checkSignature(signature, timestamp, nonce);
            if (signature.equals(digest)){
                response.getOutputStream().write(echostr.getBytes());
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 消息处理
     * @param request
     * @param response
     */
    /*@RequestMapping(value = "/wechat.do", method = RequestMethod.POST)
    public void process(HttpServletRequest request, HttpServletResponse response) {
        // 获取消息
        Document docRecv = null;
        try {
            docRecv = new SAXReader().read(request.getInputStream());
        } catch (Exception e){
            log.error(e.getMessage());
        }

        // 根节点
        Element rootRecv = docRecv.getRootElement();

        // 解析消息
        String type = rootRecv.elementText("MsgType");
        String from = rootRecv.elementText("FromUserName");
        String to = rootRecv.elementText("ToUserName");
        String text = rootRecv.element("Content").getText();

        // 构建响应
        Document docSend = docFactory.createDocument();
        Element rootSend = docSend.addElement(new QName("xml"));

        Element eleFromUserName = docFactory.createElement(new QName("FromUserName"));
        eleFromUserName.addCDATA(to);
        rootSend.add(eleFromUserName);

        rootSend.addElement("ToUserName");
        rootSend.element("ToUserName").addCDATA(from);

        rootSend.addElement("CreateTime");
        rootSend.element("CreateTime").setText(Integer.toString((int)System.currentTimeMillis()));

        rootSend.addElement("MsgType");
        rootSend.element("MsgType").addCDATA(type);

        rootSend.addElement("Content");
        rootSend.element("Content").addCDATA(text);

        try {
            // 输出消息
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(docSend.asXML());
        } catch (IOException ioe){
            log.error(ioe.getMessage());
        }
    }*/

    /**
     * 消息处理
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wechat.do", method = RequestMethod.POST)
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String digest = null;
        try {
            // 消息验证
            digest = WechatUtil.checkSignature(token, timestamp, nonce);
        } catch (NoSuchAlgorithmException nsae){
            log.error(nsae.getMessage());
        }

        if (signature.equals(digest)){
            // 消息接收
            String msgRecv = IOUtils.toString(request.getInputStream());

            // 消息转发
            String msgSend = dispatch.excute(msgRecv);

            // 消息输出
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(msgSend);
        } else {
            log.error("fail to check check signature");
        }
    }

    /**
     * 授权回调
     * @param request
     * @param model
     */
    @RequestMapping(value = "/callback.do")
    private String callback(HttpServletRequest request, ModelMap model){
        model.addAttribute("code", request.getParameter("code"));
        return "wechat/callback";
    }
}