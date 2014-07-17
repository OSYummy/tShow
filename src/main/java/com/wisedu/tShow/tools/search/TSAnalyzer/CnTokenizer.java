package com.wisedu.tShow.tools.search.TSAnalyzer;

import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-7
 * Time: 下午10:53
 * To change this template use File | Settings | File Templates.
 */
public class CnTokenizer  extends Tokenizer {

    protected CnTokenizer(Reader input) {
        super(input);
    }

    protected CnTokenizer(AttributeFactory factory, Reader input) {
        super(factory, input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        return false;
    }

    /**
     * 逆向邻接表
     */
    private class AdjacencyList {
        private int _vertexNum;
        private LinkedList _vertexList[];

        /**
         * 构造n个点的邻接表
         * @param n 图的节点数
         */
        public AdjacencyList(int n){
            _vertexNum = n;
            _vertexList = new LinkedList[_vertexNum];
        }

        /**
         * 添加边
         * @param token 词元
         */
        public void addEdge(CnToken token){
            LinkedList list = _vertexList[token.getEnd()];
            if (list == null){
                list = new LinkedList<CnToken>();
            }
            list.add(token);
        }
    }
}
