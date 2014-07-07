package com.wisedu.core.common.tools.search.token;

/**
 * 中文分词的词元
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-7
 * Time: 下午10:38
 * To change this template use File | Settings | File Templates.
 */
public class CnToken {
    private String _text;    //词;

    private String _type;    //词性;

    private int _start;          //起始位置;

    private int _end;            //结束位置;

    private int  _freq;          //词频;

    public CnToken() {
    }

    public CnToken(String text, String type, int start, int end, int freq) {
        _text = text;
        _type = type;
        _start = start;
        _end = end;
        _freq = freq;
    }

    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = text;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public int getStart() {
        return _start;
    }

    public void setStart(int start) {
        _start = start;
    }

    public int getEnd() {
        return _end;
    }

    public void setEnd(int end) {
        _end = end;
    }

    public int getFreq() {
        return _freq;
    }

    public void setFreq(int freq) {
        _freq = freq;
    }
}
