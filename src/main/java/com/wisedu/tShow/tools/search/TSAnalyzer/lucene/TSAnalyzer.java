package com.wisedu.tShow.tools.search.TSAnalyzer.lucene;

import org.apache.lucene.analysis.Analyzer;

import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-17
 * Time: 下午6:26
 * To change this template use File | Settings | File Templates.
 */
public class TSAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        return null;
    }
}
