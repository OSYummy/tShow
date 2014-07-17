package com.wisedu.core.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-8
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public class PropertyConfigurerUtil extends PropertyPlaceholderConfigurer {
    private static Properties ctxProperties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        ctxProperties = props;
        super.processProperties(beanFactoryToProcess, props);
    }

    public static Object getProperty(String key){
        return ctxProperties.get(key);
    }

    public static String getProperty(String key, String dft){
        Object value = getProperty(key);
        return (value!=null)? (String)value: dft;
    }

    public static Boolean getProperty(String key, Boolean dft){
        Object value = getProperty(key);
        return (value!=null)? (Boolean)value: dft;
    }
}
