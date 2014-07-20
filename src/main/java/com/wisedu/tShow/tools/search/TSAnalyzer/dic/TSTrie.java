package com.wisedu.tShow.tools.search.TSAnalyzer.dic;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

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
        // 词条
        protected Term term;

        public Node(char splitchar) {
            this(null, splitchar);
        }

        public Node(Term term, char splitchar) {
            this.term = term;
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
     * @param term 词条信息
     */
    public void insert(String word, Term term) {
        insert(root, word, term);
    }

    /**
     * 插入字符串至节点
     * @param node 节点
     * @param word 字符串
     * @param term 词条信息
     */
    public void insert(Node node, String word, Term term){
        int offset = 0;
        Node current = node;
        while (offset < word.length()){
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
                if (current.eqkid == null && offset < word.length()){
                    current.eqkid = new Node(word.charAt(offset));
                }
                if (offset == word.length()){
                    current.term = term;
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

    public List<Term> match(String word){
        return match(root, word);
    }

    public List<Term> match(Node node, String word){
        List<Term> terms = new ArrayList<Term>();

        int offset = 0;
        Node current = node;
        while (offset < word.length()){
            char c = word.charAt(offset);
            int cmp = c - current.splitchar;
            if (cmp > 0){
                current = current.hikid;
            } else if (cmp == 0){
                offset++;
                if (current.term != null){
                    terms.add(current.term);
                }
                current = current.eqkid;
            } else {
                current = current.lokid;
            }
        }

        return terms;
    }
}
