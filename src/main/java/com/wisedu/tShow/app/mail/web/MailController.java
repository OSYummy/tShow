package com.wisedu.tShow.app.mail.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/index.do")
    public String index(){
        return "mail/index";
    }

    @RequestMapping("/redirect.do")
    public String redirect(HttpServletRequest request, HttpServletResponse response){
        return "mail/redirect";
    }
}