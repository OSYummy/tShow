package com.wisedu.tShow.tools.wechat.utils;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.wisedu.tShow.tools.wechat.types.DataType;
import org.dom4j.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-13
 * Time: 下午6:35
 * To change this template use File | Settings | File Templates.
 */
public class EntityUtil {
    /**
     * 填充实体
     * @param entity 待填充的实体
     * @param root XML消息的根节点
     */
    public static void FillEntityWithXml(Object entity, Element root) throws IllegalAccessException{
        List<Element> elements = root.elements();
        for (Element element: elements){
            String fieldName = element.getName();
            setDeclaredField(entity, fieldName, element);
        }
    }

    /**
     * 填充域
     * @param entity 待填充的实体
     * @param fieldName 填充域名称
     * @param element XML的当前节点
     */
    private static void setDeclaredField(final Object entity, final String fieldName, final Element element) throws IllegalAccessException{
        // 获取当前域
        Field field = getDeclaredField(entity.getClass(), fieldName);
        if (field == null){
            return;
        }

        // 修改访问权限
        if (!Modifier.isPublic(field.getModifiers())
                || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }

        try {
            // 填充域
            String text = new String(element.getText().getBytes(), "utf-8");
            String dataType = field.getType().getName();
            switch(DataType.valueOf(dataType.substring(dataType.lastIndexOf('.')+1))){
                case Boolean:
                    field.set(entity, Boolean.parseBoolean(text));
                case Integer:
                    field.set(entity, new Integer(Integer.parseInt(text)));
                    break;
                case Long:
                    field.set(entity, Long.parseLong(text));
                    break;
                case String:
                    field.set(entity, text);
                    break;
                case Date:
                    field.set(entity, new Date(Long.parseLong(text)));
                case List: case LinkedList: case ArrayList:
                    switch (DataType.valueOf(element.elements().get(0).getName())){
                        case Article:
                            List<Object> articles = new ArrayList<Object>();
                            for (Element o: element.elements()){
                                Object article = new Object();
                                FillEntityWithXml(article, o);
                                articles.add(article);
                            }
                            field.set(entity, articles);
                            break;
                        default:
                            field.set(entity, null);
                            break;
                    }
                    break;
                default:
                    field.set(entity, null);
                    break;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 获取类的属性
     * @param clazz 待获取类
     * @param fieldName 属性名
     */
    private static Field getDeclaredField(final Class clazz, final String fieldName) {
        for (Class superClass=clazz; superClass!=Object.class; superClass=superClass.getSuperclass()){
            try {
                // 在当前类获取不到，则到父类获取
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
            }
        }
        return null;
    }

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