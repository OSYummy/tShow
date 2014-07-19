package com.wisedu.tShow.tools.search.TSAnalyzer.dic;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-19
 * Time: 下午9:33
 * To change this template use File | Settings | File Templates.
 */
public class TSTrie {
    public class Node {
        // 节点的字符
        protected char splitchar;
        // 子节点
        protected Node lokid, eqkid, hikid;

        public Node(char splitchar) {
            this.splitchar = splitchar;
        }
    }

    // 节点数
    private Integer n;

    // 根节点
    private Node root = new Node('\0');

    /**
     * 插入字符串至节点
     * @param word 字符串
     */
    public void insert(String word) {
        insert(root, word);
    }

    /**
     * 插入字符串至节点
     * @param node 节点
     * @param word 字符串
     */
    public void insert(Node node, String word){
        int index = 0;
        Node current = node;
        while (index < word.length()){
            char c = word.charAt(index);
            int cmp = c - current.splitchar;
            if (cmp > 0){
                if (current.hikid == null){
                    current.hikid = new Node(
                            word.charAt(index)
                    );
                }
                current = current.hikid;
            } else if (cmp == 0){
                index++;
                if (current.eqkid == null && index < word.length()){
                    current.eqkid = new Node(word.charAt(index));
                }
                current = current.eqkid;
            } else {
                if (current.lokid == null){
                    current.lokid = new Node(
                            word.charAt(index)
                    );
                }
                current = current.lokid;
            }
        }
    }

    public void match(String test){

    }
}
