package com.wisedu.core.common.tools.dirty.doc;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-8
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class TopDocs implements Serializable, Cloneable {
    private int totalHitsCount;

    private Doc[] docs;

    public TopDocs() {
        this(0, null);
    }

    public TopDocs(int totalHitsCount, Doc[] docs) {
        this.totalHitsCount = totalHitsCount;
        this.docs = docs;
    }

    public int getTotalHitsCount() {
        return totalHitsCount;
    }

    public void setTotalHitsCount(int totalHitsCount) {
        this.totalHitsCount = totalHitsCount;
    }

    public Doc[] getDocs() {
        return docs;
    }

    public void setDocs(Doc[] docs) {
        this.docs = docs;
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
