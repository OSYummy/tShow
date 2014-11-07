package com.wisedu.tShow.app.wechat.web;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.wisedu.core.utils.PropertyConfigurerUtil;
import com.wisedu.tShow.tools.wechat.WechatApiDispatcher;
import com.wisedu.tShow.utils.WechatUtil;
import net.sf.json.JSON;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(value="/wechat")
public class WechatController {
    private final static Logger log = LoggerFactory.getLogger(WechatController.class);

    // 企业号
    private static final String qyToken = (String)PropertyConfigurerUtil.getProperty("app.wechat.qy.token");
    private static final String qyCorpID = (String)PropertyConfigurerUtil.getProperty("app.wechat.qy.CorpID");
    private static final String qyEncodingAESKey = (String)PropertyConfigurerUtil.getProperty("app.wechat.qy.EncodingAESKey");

    @RequestMapping(value = "/qy/message.do", method = RequestMethod.GET)
    public void qyCheck(HttpServletRequest request, HttpServletResponse response){
        // 加密签名
        String sVerifyMsgSig = request.getParameter("msg_signature");
        // 时间戳
        String sVerifyTimeStamp = request.getParameter("timestamp");
        // 随机数
        String sVerifyNonce = request.getParameter("nonce");
        // 随机字符串
        String sVerifyEchoStr = request.getParameter("echostr");

        String sEchoStr = null;
        try {
            WXBizMsgCrypt wxcpt
                    = new WXBizMsgCrypt(qyToken, qyEncodingAESKey, qyCorpID);
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
            response.getWriter().write(sEchoStr);
        } catch (AesException aese) {
            log.error(aese.getMessage(), aese);
        } catch (IOException ioe){
            log.error(ioe.getMessage(), ioe);
        }
    }

    @RequestMapping(value = "/qy/message.do", method = RequestMethod.POST)
    public void qyMessage(HttpServletRequest request, HttpServletResponse response){
        // 加密签名
        String sReqMsgSig = request.getParameter("msg_signature");
        // 时间戳
        String sReqTimeStamp = request.getParameter("timestamp");
        // 随机数
        String sReqNonce = request.getParameter("nonce");
        // 随机字符串
        String sVerifyEchoStr = request.getParameter("echostr");

        String sMsg = null;
        WXBizMsgCrypt wxcpt = null;
        try {
            wxcpt = new WXBizMsgCrypt(qyToken, qyEncodingAESKey, qyCorpID);

            String line = null;
            StringBuffer sReqData = new StringBuffer();
            BufferedReader sb = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            while ((line=sb.readLine()) != null){
                sReqData.append(line);
            }
            sMsg = wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, sReqData.toString());
            System.out.println(sMsg);
        } catch (AesException aese){
            log.error(aese.getMessage());
        } catch (IOException ioe){
            log.error(ioe.getMessage());
        }

        Document document = null;
        if (sMsg != null){
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                document = db.parse(new InputSource(new StringReader(sMsg)));
            } catch (ParserConfigurationException pce){
                log.error(pce.getMessage());
            } catch (SAXException saxe){
                log.error(saxe.getMessage());
            } catch (IOException ioe){
                log.error(ioe.getMessage());
            }
        }

        if (document != null){
            Element root = document.getDocumentElement();
            CDATASection toUserNode = (CDATASection)(root.getElementsByTagName("ToUserName").item(0).getChildNodes().item(0));
            String toUserName = toUserNode.getData();

            CDATASection fromUserNode = (CDATASection)(root.getElementsByTagName("FromUserName").item(0).getChildNodes().item(0));
            String fromUserName = fromUserNode.getData();

            Node createTimeNode = root.getElementsByTagName("CreateTime").item(0);

            toUserNode.setData(fromUserName);
            fromUserNode.setData(toUserName);
            createTimeNode.setTextContent(Integer.toString((int)System.currentTimeMillis()));

            Node msgTypeNode = root.getElementsByTagName("MsgType").item(0);

            if (msgTypeNode!=null && "text".equals(msgTypeNode.getTextContent())){
                String tlcl = "http://billie66.github.io/TLCL/";
                CDATASection contentNode = (CDATASection)(root.getElementsByTagName("Content").item(0).getChildNodes().item(0));
                contentNode.setData("<a href=\"" + tlcl + "\">tlcl</a><p style=\"color:red\">Style</p>");

                root.removeChild(root.getElementsByTagName("MsgId").item(0));
                root.removeChild(root.getElementsByTagName("AgentID").item(0));
            }
        }



        String sEncryptMsg = null;
        if (wxcpt!=null && document!=null){
            StringWriter sw = new StringWriter();
            try {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                StreamResult sr = new StreamResult(sw);
                transformer.transform(new DOMSource(document.getDocumentElement()), sr);

                sEncryptMsg = wxcpt.EncryptMsg(sr.getWriter().toString(), sReqTimeStamp, sReqNonce);
                System.out.println(sEncryptMsg);
            } catch (TransformerConfigurationException tce){
                log.error(tce.getMessage());
            } catch (TransformerException tfe){
                log.error(tfe.getMessage());
            } catch (AesException aese){
                log.error(aese.getMessage());
            } finally {
                try {
                    sw.close();
                } catch (IOException ioe){
                }
            }
        }

        if (sEncryptMsg != null){
            try {
                response.setContentType("text/xml");
                response.getWriter().write(sEncryptMsg);
            } catch (IOException ioe){
                log.error(ioe.getMessage());
            }
        }
    }

    @RequestMapping(value = "/qy/more.do")
    public String qyMore(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String code = request.getParameter("code");
        String agentid = "4";
        String accessToken = "KqwpKbEUoj3PAWMKirEfzMcOkMy3ig6YuRJmzOe4uuY";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?"
                + "access_token=" + accessToken + "&code=" + code + "&agentid=" + agentid;

        try {
            HttpURLConnection con = (HttpURLConnection)new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            InputStream in = con.getInputStream();
            JSONObject jsonObject = new JSONObject(
                    new JSONTokener(new BufferedReader(new InputStreamReader(in, "utf-8")))
            );
            in.close();

            model.addAttribute("userid", jsonObject.getString("UserId"));
            model.addAttribute("deviceid", jsonObject.getString("DeviceId"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return "wechat/more";
    }

    // 公众号
    private final static String mpToken = (String)PropertyConfigurerUtil.getProperty("app.wechat.mp.token");
    private final static String mpAppId = (String)PropertyConfigurerUtil.getProperty("app.wechat.mp.appId");
    private final static String mpAppSecret = (String)PropertyConfigurerUtil.getProperty("app.wechat.mp.appSecret");

    private static WechatApiDispatcher dispatcher = new WechatApiDispatcher();

    /**
     * 服务器配置验证
     * @param request
     * @param response
     */
    @RequestMapping(value = "/mp/index.do", method = RequestMethod.GET)
    public void mpCheck(HttpServletRequest request, HttpServletResponse response){
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
            String digest = WechatUtil.checkSignature(mpToken, timestamp, nonce);
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
    @RequestMapping(value = "/mp/index.do", method = RequestMethod.POST)
    public void mpProcess(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String digest = null;
        try {
            // 签名验证
            digest = WechatUtil.checkSignature(mpToken, timestamp, nonce);
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
    @RequestMapping(value = "/mp/callback.do")
    private String mpCallback(HttpServletRequest request, ModelMap model){
        model.addAttribute("code", request.getParameter("code"));
        return "wechat/callback";
    }
}