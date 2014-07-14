package com.wisedu.tShow.tools.wechat.utils;

import com.thoughtworks.xstream.XStream;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public class XStreamUtil {
    private final static XStream stream = new XStream();

    public static <T> T fromXml(String xml, T object){
        stream.processAnnotations(object.getClass());
        T obj = (T)stream.fromXML(xml, object);
        return obj;
    }
}
