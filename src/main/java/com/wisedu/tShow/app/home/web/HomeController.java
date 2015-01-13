package com.wisedu.tShow.app.home.web;

import com.wisedu.core.exception.ServiceCode;
import com.wisedu.core.exception.ServiceException;
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

    @RequestMapping(value="/mgmt.do")
    public String mgmt(ModelMap model){
        Integer errCode = null;
        String errMsg = null;
        try {
            model.addAttribute("greet", "我是首页");

            errCode = ServiceCode.SERVICE_OK.getErrCode();
            errMsg = ServiceCode.SERVICE_OK.getErrMsg();
        } catch (ServiceException se) {
            if (se.getErrCode() == null){
                errCode = se.getErrCode();
                errMsg = se.getErrMsg();
            } else {
                log.error(se.getMessage());
                errCode = ServiceCode.SERVICE_ERROR.getErrCode();
                errMsg = ServiceCode.SERVICE_ERROR.getErrMsg();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            errCode = ServiceCode.SERVICE_ERROR.getErrCode();
            errMsg = ServiceCode.SERVICE_ERROR.getErrMsg();
        }
        model.addAttribute("errCode", errCode);
        model.addAttribute("errMsg", errMsg);
        return "home/index";
    }
}
