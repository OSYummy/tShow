package com.wisedu.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-18
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class BeanUtil {
    public static Object getFieldValue(final Object object, final String fieldName){
        Field field=getDeclaredField(object, fieldName);

        if (field == null)
            throw new IllegalArgumentException("Could not find field ["+ fieldName + "] on target [" + object + "]");

        makeAccessible(field);

        Object result=null;
        try {
            result=field.get(object);
        } catch (IllegalAccessException e){
            throw new RuntimeException("never happend exception!", e);
        }

        return result;
    }

    public static void setFieldValue(final Object object, final String fieldName, final Object value){
        Field field=getDeclaredField(object, fieldName);

        if (field == null)
            throw new IllegalArgumentException("Could not find field ["+ fieldName + "] on target [" + object + "]");

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e){
            throw new RuntimeException("never happend exception!", e);
        }
    }

    protected static Field getDeclaredField(final Object object, final String fieldName){
        return getDeclaredField(object.getClass(), fieldName);
    }

    protected static Field getDeclaredField(final Class clazz, final String fieldName){
        for (Class superClass=clazz; superClass!=Object.class; superClass=superClass.getSuperclass()){
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e){
                //catch自getDeclaredField抛出的NoSuchFieldException,继续执行for循环
            }
        }
        return null;
    }

    protected static void makeAccessible(final Field field){
        if(!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers()))
            field.setAccessible(true);
    }

    public static Object getSimpleProperty(Object bean, String propName)
            throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        return bean.getClass().getMethod(getReadMethod(propName)).invoke(bean);
    }

    private static String getReadMethod(String name){
        return "get"+name.substring(0, 1).toUpperCase(Locale.ENGLISH)+name.substring(1);
    }
}