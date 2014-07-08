package com.wisedu.core.common.tools.search.token;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-7
 * Time: 下午10:53
 * To change this template use File | Settings | File Templates.
 */
public class CnTokenizer {

    /**
     * 逆向邻接表
     */
    private class AdjacencyList {
        private LinkedList _vertexList[];

        /**
         * 构造n个点的邻接表
         * @param n 图的节点数
         */
        public AdjacencyList(int n){
            _vertexList = new LinkedList[n];
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
