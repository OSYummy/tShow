package com.wisedu.tShow.app.mail.web;

import com.wisedu.core.common.utils.PropertyConfigurerUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-22
 * Time: 上午9:07
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value="/mail")
public class MailController {
    private final static Logger log = LoggerFactory.getLogger(MailController.class);

    // 客户端ID
    private final static String appClientId = PropertyConfigurerUtil.getProperty("app.outlook.appId", "");

    // 客户端密钥
    private final static String appClientSecret = PropertyConfigurerUtil.getProperty("app.outlook.appSecret", "");

    @RequestMapping("/index.do")
    public String index(){
        return "mail/index";
    }

    @RequestMapping("/redirect.do")
    public String redirect(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (request.getQueryString()==null){
            return "mail/redirect";
        }

        HttpClient client=new DefaultHttpClient();
        HttpPost method = new HttpPost("https://login.live.com/oauth20_token.srf");

        List<NameValuePair> data=new ArrayList<NameValuePair>();

        data.add(new BasicNameValuePair("client_id", appClientId));
        data.add(new BasicNameValuePair("client_secret", appClientSecret));
        data.add(new BasicNameValuePair("redirect_uri", "http://outlook.ngrok.com/tShow/mail/redirect.do"));
        data.add(new BasicNameValuePair("code", request.getParameter("code")));
        data.add(new BasicNameValuePair("grant_type", "authorization_code"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data, "utf-8");
        entity.setContentType("application/x-www-form-urlencoded");
        method.setEntity(entity);

        HttpResponse result = client.execute(method);
        System.out.println(result.getStatusLine());
        System.out.println(EntityUtils.toString(result.getEntity(), "utf-8"));

        return "mail/redirect";
    }
}