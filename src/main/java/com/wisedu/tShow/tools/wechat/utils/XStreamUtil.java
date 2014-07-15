package com.wisedu.tShow.tools.wechat.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;
import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-14
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public class XStreamUtil {
    private final static XStream stream = new XStream(
            new XppDriver(){
                @Override
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out){
                        boolean cdata = false;
                        Class<?> targetClass = null;

                        public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                            super.startNode(name, clazz);
                            //业务处理，对于用XStreamCDATA标记的Field，需要加上CDATA标签
                            if(!name.equals("xml")){//代表当前处理节点是class，用XstreamAlias把class的别名改成xml，下面的代码片段有提到;
                                cdata = needCDATA(targetClass, name);
                            } else {
                                targetClass = clazz;
                            }
                        }

                        protected void writeText(QuickWriter writer, String text) {
                            if (cdata) {
                                writer.write("<![CDATA[");
                                writer.write(text);
                                writer.write("]]>");
                            } else {
                                writer.write(text);
                            }
                        }

                        private  boolean needCDATA(Class<?> targetClass, String fieldAlias){
                            boolean cdata = false;
                            //first, scan self
                            cdata = existsCDATA(targetClass, fieldAlias);

                            if(cdata) return cdata;
                            //if cdata is false, scan supperClass until java.lang.Object

                            Class<?> superClass = targetClass.getSuperclass();

                            while(!superClass.equals(Object.class)){
                                cdata = existsCDATA(superClass, fieldAlias);
                                if(cdata) return cdata;
                                superClass = superClass.getClass().getSuperclass();
                            }

                            return false;
                        }

                        private boolean existsCDATA(Class<?> clazz, String fieldAlias){
                            //scan fields
                            Field[] fields = clazz.getDeclaredFields();
                            for (Field field : fields) {
                                //1. exists XStreamCDATA
                                if(field.getAnnotation(XStreamCDATA.class) != null ){
                                    XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
                                    //2. exists XStreamAlias
                                    if(null != xStreamAlias){
                                        if(fieldAlias.equals(xStreamAlias.value()))//matched
                                            return true;
                                    }else{// not exists XStreamAlias
                                        if(fieldAlias.equals(field.getName()))
                                            return true;
                                    }
                                }
                            }
                            return false;
                        }
                    };
                }
            }
    );

    public static <T> T fromXml(String xml, T object){
        stream.processAnnotations(object.getClass());
        stream.ignoreUnknownElements();
        T obj = (T)stream.fromXML(xml, object);
        return obj;
    }

    public static <T> String toXml(T object){
        stream.processAnnotations(object.getClass());
        return stream.toXML(object);
    }
}
