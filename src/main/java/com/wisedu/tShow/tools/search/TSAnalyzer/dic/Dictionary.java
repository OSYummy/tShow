package com.wisedu.tShow.tools.search.TSAnalyzer.dic;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-17
 * Time: 下午6:39
 * To change this template use File | Settings | File Templates.
 */
public class Dictionary {
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

    public void insert(String word) {
        insert(root, word);
    }

    public void insert(Node node, String word){
        /*char c = word.charAt(0);
        if (node == null){
            node = new Node(c);
        }

        int index = 0;
        Node current = node;
        while (true){
            int cmp=word.charAt(index)-current.splitchar;
            if (cmp > 0){
                current = current.hikid;
            } else if (cmp == 0){

            } else {
                current = current.lokid;
            }
        }*/
        int index = 0;
        Node current = node;
        while (index < word.length()){
            char c = word.charAt(index);
            int cmp = c - current.splitchar;
            if (cmp > 0){
                index++;
                current = current.hikid;
            } else if (cmp == 0){

            } else {
                index++;
                current = current.lokid;
            }
        }
    }
}
