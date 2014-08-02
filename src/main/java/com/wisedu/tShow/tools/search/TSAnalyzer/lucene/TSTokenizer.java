package com.wisedu.tShow.tools.search.TSAnalyzer.lucene;

import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-7
 * Time: 下午10:53
 * To change this template use File | Settings | File Templates.
 */
public class TSTokenizer extends Tokenizer {

    protected TSTokenizer(Reader input) {
        super(input);
    }

    protected TSTokenizer(AttributeFactory factory, Reader input) {
        super(factory, input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        return false;
    }
}
