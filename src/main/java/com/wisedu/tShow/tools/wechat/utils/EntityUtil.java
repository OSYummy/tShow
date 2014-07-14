package com.wisedu.tShow.tools.wechat.utils;

import com.wisedu.tShow.tools.wechat.types.DataType;
import org.dom4j.Element;

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
}