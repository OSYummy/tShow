package com.wisedu.tShow.app.mail.web;

import com.wisedu.core.utils.PropertyConfigurerUtil;
import com.wisedu.tShow.app.mail.bo.Token;
import com.wisedu.tShow.app.mail.service.TokenService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TokenService tokenService;

    // 客户端ID
    private final static String appClientId
            = PropertyConfigurerUtil.getProperty("app.outlook.appId", "");

    // 客户端密钥
    private final static String appClientSecret
            = PropertyConfigurerUtil.getProperty("app.outlook.appSecret", "");

    // 回调地址
    private final static String redirectUri
            = PropertyConfigurerUtil.getProperty("app.outlook.redirect_uri", "");

    @RequestMapping("/index.do")
    public String index(ModelMap model){
        model.put("appClientId", appClientId);
        model.put("redirectUri", redirectUri);
        return "mail/index";
    }

    @RequestMapping("/validate.do")
    public void validate(HttpServletRequest request, HttpServletResponse response){
        String userId = request.getParameter("userId");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        List<Token> tokens = tokenService.listToken(params);
        if (tokens==null || tokens.size()==0){
            Token token = new Token();
            token.setUserId(userId);
            tokenService.saveToken(token);
        }
    }

    @RequestMapping("/operate.do")
    public String operate(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        String userId = request.getParameter("userId");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        List<Token> tokens = tokenService.listToken(params);
        if (tokens!=null && tokens.size()>0){
            Token token = tokens.get(0);
            model.put("refreshToken", token.getRefreshToken());
        } else {
            Token token = new Token();
            model.put("accessToken", "");
        }
        return "mail/operate";
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
        data.add(new BasicNameValuePair("redirect_uri", redirectUri));
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