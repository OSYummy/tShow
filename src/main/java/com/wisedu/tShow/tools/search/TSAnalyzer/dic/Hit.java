package com.wisedu.tShow.tools.search.TSAnalyzer.dic;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-8-2
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class Hit {
    private final static boolean UNMATCH = false;

    private final static boolean MATCHED = true;

    // 开始位置
    private int begin;

    // 结束位置
    private int end;

    public Hit() {

    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
