package com.wisedu.tShow.tools.search.TSAnalyzer.dic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-17
 * Time: 下午6:39
 * To change this template use File | Settings | File Templates.
 */
public class Dictionary {
    private static Dictionary _dic;

    private Dictionary(){
    }

    public static Dictionary getInstance(){
        if (_dic == null){
            _dic = new Dictionary();
        }
        return _dic;
    }

    public void load(File file){
    }

    private class Term{

    }

    // Ternary Search Trie
    private class TSTrie {
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
                        current.term = term;
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
        public List<Term> match(String text, int offset){
            return match(root, text, offset);
        }

        /**
         * 文本匹配
         * @param node 起始节点
         * @param text 待匹配文本
         * @param offset 文本偏移量
         */
        public List<Term> match(Node node, String text, int offset){
            List<Term> terms = new ArrayList<Term>();

            Node current = node;
            while (true){
                char c = text.charAt(offset);
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
                if (offset==text.length()
                        || current==null){
                    break;
                }
            }

            return terms;
        }
    }
}
