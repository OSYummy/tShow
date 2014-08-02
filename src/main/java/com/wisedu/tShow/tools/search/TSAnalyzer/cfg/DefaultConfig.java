package com.wisedu.tShow.tools.search.TSAnalyzer.cfg;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-8-2
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
public class DefaultConfig implements Configuration{
    // 主词典路径
    private final static String PATH_DIC_MAIN = "com.wisedu.tShow.tools.search.TSAnalyzer.dic.main.dic";

    @Override
    public String getMainDictionary() {
        return PATH_DIC_MAIN;
    }
}
