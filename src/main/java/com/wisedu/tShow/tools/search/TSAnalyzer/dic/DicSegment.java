package com.wisedu.tShow.tools.search.TSAnalyzer.dic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-8-2
 * Time: 下午1:52
 * To change this template use File | Settings | File Templates.
 */
// Ternary Search Trie
public class DicSegment {
    // 节点
    public class Node {
        // 节点的字符
        protected char splitchar;
        // 子节点
        protected Node lokid, eqkid, hikid;
        // 词条
        protected Hit hit;

        public Node(char splitchar) {
            this(null, splitchar);
        }

        public Node(Hit hit, char splitchar) {
            this.hit = hit;
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
     * @param hit 词条信息
     */
    public void insert(String word, Hit hit) {
        insert(root, word, hit);
    }

    /**
     * 插入字符串至节点
     * @param node 节点
     * @param word 字符串
     * @param hit 词条信息
     */
    public void insert(Node node, String word, Hit hit){
        int offset = 0;
        Node current = node;
        while (true){
            char c = word.charAt(offset);
            int cmp = c - current.splitchar;
            if (cmp > 0){
                if (current.hikid == null){
                    current.hikid = new Node(
                            word.charAt(offset)
                    );
                }
                current = current.hikid;
            } else if (cmp == 0){
                offset++;
                if(offset == word.length()){
                    current.hit = hit;
                    break;
                }
                if (current.eqkid == null){
                    current.eqkid = new Node(
                            word.charAt(offset)
                    );
                }
                current = current.eqkid;
            } else {
                if (current.lokid == null){
                    current.lokid = new Node(
                            word.charAt(offset)
                    );
                }
                current = current.lokid;
            }
        }
    }

    /**
     * 文本匹配
     * @param text 待匹配文本
     * @param offset 文本偏移量
     */
    public List<Hit> match(String text, int offset){
        return match(root, text, offset);
    }

    /**
     * 文本匹配
     * @param node 起始节点
     * @param text 待匹配文本
     * @param offset 文本偏移量
     */
    public List<Hit> match(Node node, String text, int offset){
        List<Hit> terms = new ArrayList<Hit>();

        Node current = node;
        while (true){
            char c = text.charAt(offset);
            int cmp = c - current.splitchar;
            if (cmp > 0){
                current = current.hikid;
            } else if (cmp == 0){
                offset++;
                if (current.hit != null){
                    terms.add(current.hit);
                }
                current = current.eqkid;
            } else {
                current = current.lokid;
            }
            if (offset==text.length()
                    || current==null){
                break;
            }
        }

        return terms;
    }
}
