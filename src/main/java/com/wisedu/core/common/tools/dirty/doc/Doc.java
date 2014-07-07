package com.wisedu.core.common.tools.dirty.doc;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-9
 * Time: 上午10:05
 * To change this template use File | Settings | File Templates.
 */
public class Doc implements Serializable, Cloneable {
    private long offset;

    private String pattern;

    public Doc() {
        this("\0", 0L);
    }

    public Doc(String pattern, long offset) {
        this.pattern = pattern;
        this.offset = offset;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Object clone(){
        Object o = null;
        try{
            o=super.clone();
        }catch(Exception e){
            e.printStackTrace();
            o=null;
        }
        return o;
    }
}
