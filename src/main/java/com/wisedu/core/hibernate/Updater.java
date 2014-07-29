package com.wisedu.core.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-18
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class Updater<T> {
    private T bean;
    private UPDATE_MODE mode=UPDATE_MODE.MIDDLE;
    private Set<String> includes=new HashSet<String>();
    private Set<String> excludes=new HashSet<String>();

    public static enum UPDATE_MODE{
        MAX, MIDDLE, MIN
    }

    public Updater(T bean) {
        this.bean = bean;
    }

    public Updater(T bean, UPDATE_MODE mode) {
        this.bean = bean;
        this.mode = mode;
    }

    public T getBean() {
        return bean;
    }

    public boolean isUpdate(String name, Object value){
        if (this.mode == UPDATE_MODE.MAX){
            return !excludes.contains(name);
        } else if (this.mode == UPDATE_MODE.MIN){
            return includes.contains(name);
        } else if (this.mode == UPDATE_MODE.MIDDLE){
            if (value == null)
                return includes.contains(name);
            else return excludes.contains(name);
        } else {

        }

        return true;
    }

    public Updater<T> include(String prop){
        includes.add(prop);
        return this;
    }

    public Updater<T> exclude(String prop){
        excludes.add(prop);
        return this;
    }
}