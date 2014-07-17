package com.wisedu.tShow.tools.wechat.utils;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-13
 * Time: 下午6:35
 * To change this template use File | Settings | File Templates.
 */
public class EntityUtil {
    public static <T> T fromXml(String xml, T object) throws IOException{
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());

            Unmarshaller unMarshaller = context.createUnmarshaller();

            return (T)unMarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException jaxbe){
            throw new IOException("fail to unMarshal xml", jaxbe);
        }
    }

    public static <T> String asXml(T object) throws IOException{
        StringWriter writer = null;
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());

            Marshaller marshaller = context.createMarshaller();

            // 是否省略xm头声明信息
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            // 设置编码
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
            // 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // 设置转义
            marshaller.setProperty(
                    CharacterEscapeHandler.class.getName(),
                    new CharacterEscapeHandler() {
                        public void escape(char[] ac, int i, int j, boolean flag, Writer writer)
                                throws IOException {
                            writer.write(ac, i, j);
                        }
                    }
            );

            // 输出
            writer = new StringWriter();
            marshaller.marshal(object, writer);
        } catch (JAXBException jaxbe){
            throw new IOException("fail to marshal object", jaxbe);
        }
        return (writer != null)? writer.toString(): null;
    }
}