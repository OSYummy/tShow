package com.wisedu.tShow.tools.wechat.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-16
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
public class AdapterCDATA extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String v) throws Exception {
        return v;
    }

    @Override
    public String marshal(String v) throws Exception {
        return "<![CDATA[" + v + "]]>";
    }
}
