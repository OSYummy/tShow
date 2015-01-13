package com.wisedu.tShow.app.home.web;

import com.wisedu.core.exception.ServiceCode;
import com.wisedu.core.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public void mgmt(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String errMsg = null;
        Integer errCode = null;
        Map data = new HashMap<String, Object>();
        try {

            errMsg = ServiceCode.SERVICE_OK.getErrMsg();
            errCode = ServiceCode.SERVICE_OK.getErrCode();
        } catch (ServiceException se) {
            if (se.getErrCode() == null){
                errMsg = se.getErrMsg();
                errCode = se.getErrCode();
            } else {
                log.error(se.getMessage());
                errMsg = ServiceCode.SERVICE_ERROR.getErrMsg();
                errCode = ServiceCode.SERVICE_ERROR.getErrCode();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            errMsg = ServiceCode.SERVICE_ERROR.getErrMsg();
            errCode = ServiceCode.SERVICE_ERROR.getErrCode();
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(
                ""
        );
    }
}
