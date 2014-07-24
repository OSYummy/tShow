package com.wisedu.tShow.tools.search.TSAnalyzer;

/**
 * 中文分词的词元
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-7
 * Time: 下午10:38
 * To change this template use File | Settings | File Templates.
 */
public class TSToken {
    private String term;    //词;

    private String type;    //词性;

    private int start;          //起始位置;

    private int end;            //结束位置;

    private int  freq;          //词频;

    public TSToken() {
    }

    public TSToken(String term, String type, int start, int end, int freq) {
        this.term = term;
        this.type = type;
        this.start = start;
        this.end = end;
        this.freq = freq;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String toString() {
        return "text: " + term + " start: " + start + " end: " + end
                +"--------type: "+type;
    }
}
