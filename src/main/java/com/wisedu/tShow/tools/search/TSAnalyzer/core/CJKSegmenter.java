package com.wisedu.tShow.tools.search.TSAnalyzer.core;

import com.wisedu.tShow.tools.search.TSAnalyzer.dic.Hit;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-13
 * Time: 下午11:25
 * To change this template use File | Settings | File Templates.
 */
class CJKSegmenter implements ISegmenter{
    //子分词器标签
    static final String SEGMENTER_NAME = "CJK_SEGMENTER";
    //待处理的分词hit队列
    private List<Hit> tmpHits;

    CJKSegmenter(){
        tmpHits = new LinkedList<Hit>();
    }

    @Override
    public void analyze(AnalyzeContext context) {

    }

    @Override
    public void reset() {
        tmpHits.clear();
    }
}
