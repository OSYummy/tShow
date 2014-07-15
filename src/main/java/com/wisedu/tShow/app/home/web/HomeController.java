package com.wisedu.tShow.app.home.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-10
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
    private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value="/index.do")
    public String index(ModelMap model){
        model.addAttribute("greet", "我是首页");
        return "home/index";
    }
}
