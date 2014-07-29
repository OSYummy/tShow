package com.wisedu.tShow.app.wechat.web;

import com.wisedu.core.utils.PropertyConfigurerUtil;
import com.wisedu.tShow.tools.wechat.WechatApiDispatcher;
import com.wisedu.tShow.utils.WechatUtil;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
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

    private static WechatApiDispatcher dispatcher = new WechatApiDispatcher();

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
            // 签名验证
            String digest = WechatUtil.checkSignature(token, timestamp, nonce);
            if (signature.equals(digest)){
                response.getWriter().write(echostr);
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
            // 签名验证
            digest = WechatUtil.checkSignature(token, timestamp, nonce);
        } catch (NoSuchAlgorithmException nsae){
            log.error(nsae.getMessage());
        }

        if (signature.equals(digest)){
            // 消息接收
            String msgRecv = IOUtils.toString(request.getInputStream(), "utf-8");

            // 消息转发
            String msgSend = null;
            try {
                msgSend = dispatcher.excute(msgRecv).asXML();

                // 消息输出
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(msgSend);
            } catch (DocumentException dme){
                log.error(dme.getMessage());
            }
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