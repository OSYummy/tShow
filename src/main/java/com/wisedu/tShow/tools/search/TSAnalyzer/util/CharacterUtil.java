package com.wisedu.tShow.tools.search.TSAnalyzer.util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-13
 * Time: 下午11:43
 * To change this template use File | Settings | File Templates.
 */
public class CharacterUtil {
    public static final int CHAR_USELESS = 0;

    public static final int CHAR_ARABIC = 0X00000001;

    public static final int CHAR_ENGLISH = 0X00000002;

    public static final int CHAR_CJK  = 0X00000004;

    /**
     * 识别字符类型
     * @param input
     * @return int CharacterUtil定义的字符类型常量
     */
    public static int identifyCharType(char input){
        return CHAR_CJK;
    }

    /**
     * 进行字符规格化（全角转半角，大写转小写处理）
     * @param input
     * @return char
     */
    public static char regularize(char input){
        return input;
    }
}
