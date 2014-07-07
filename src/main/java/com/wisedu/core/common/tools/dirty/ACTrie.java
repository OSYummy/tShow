package com.wisedu.core.common.tools.dirty;

import com.wisedu.core.common.tools.dirty.doc.Doc;
import com.wisedu.core.common.tools.dirty.doc.TopDocs;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-6
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class ACTrie {
    private TrieNode _root;

    private boolean _blocked;

    private static String separator=System.getProperty("line.separator");

    /*separator的shift表*/
    private static int shift[] = null;
    static {
        shift = new int[separator.length()];
        Arrays.fill(shift, 0);
    }

    public ACTrie(){
        this(new String[]{});
    }

    public ACTrie(String[] patterns){
        _root = new TrieNode('\0', null);
        construct(patterns);
    }

    public ACTrie(File file) throws IOException{
        _root = new TrieNode('\0', null);
        construct(file);
    }

    /**
     * 插入节点
     * @param pattern 扫描的模式
     */
    public void insertTrieNode(String pattern){
        TrieNode root = _root;
        for (char c: pattern.toCharArray()){
            TrieNode transition = root.getTransition(c);
            if (transition == null){
                transition = new TrieNode(c, root);
                root.insertTransition(transition);
            }
            root = transition;
        }
        root.pattern(pattern);
    }

    public TrieNode findTrieNode(String pattern){
        TrieNode transition = _root;
        for (char c: pattern.toCharArray()){
            if (transition.containsTransition(c)){
                transition = transition.getTransition(c);
            } else {
                return null;
            }
        }
        return transition;
    }

    public TrieNode construct(String[] patterns){
        buildTrie(patterns);
        buildFailLinks();
        return _root;
    }

    public TrieNode construct(File file)
            throws IOException{
        buildTrie(file);
        buildFailLinks();
        return _root;
    }

    /**
     * 构造Trie树
     * @param patterns 关键词
     * @return Trie树的根节点
     */
    private TrieNode buildTrie(String[] patterns){
        for (String pattern: patterns){
            insertTrieNode(pattern);
        }
        return _root;
    }

    private TrieNode buildTrie(File file)
            throws IOException{
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

            String line = null;
            while ((line=reader.readLine()) != null){
                insertTrieNode(line);
            }
        } catch (IOException ioe){
            throw new IOException(ioe.getMessage(), ioe);
        } finally {
            if (reader != null){
                reader.close();
            }
        }

        return _root;
    }

    /**
     * 构建失效链接
     * @return Trie树根节点
     */
    private TrieNode buildFailLinks(){
        /*广度优先遍历*/
        Queue<TrieNode> queue
                = new LinkedList<TrieNode>();
        queue.add(_root);
        while (!queue.isEmpty()){
            TrieNode transition = queue.poll();
            TrieNode[] transitions = transition.transitions();
            for (int i=0; i<transitions.length; i++){
                queue.add(transitions[i]);
            }

            /*root的失效链接为自己*/
            if (transition.getChar()=='\0'){
                transition.failure(_root);
                continue;
            }

            /*添加失效链接*/
            char c = transition.getChar();
            TrieNode failure = transition.parent().failure();
            while (!failure.containsTransition(c) && failure.getChar()!='\0'){
                failure = failure.failure();
            }

            if (failure.containsTransition(c)){
                transition.failure(failure.getTransition(c));
            } else {
                transition.failure(_root);
            }
            if (transition.failure() == transition){
                transition.failure(_root);
            }
        }

        return _root;
    }

    /**
     * 多模式匹配
     * @param file 待匹配的文件
     * @param n 获取前n个匹配结果
     * @throws java.io.IOException
     */
    public TopDocs matches(File file, int n)
            throws IOException{
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            TopDocs docs = matches(reader, n);
            return docs;
        } catch (IOException ioe){
            throw new IOException(ioe.getMessage(), ioe);
        } finally {
            if (reader != null){
                reader.close();
            }
        }
    }

    /**
     * 多模式匹配
     * @param text 待匹配的文件
     * @param n 获取前n个匹配结果
     * @throws java.io.IOException
     */
    public TopDocs matches(String text, int n)
            throws IOException{
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new StringReader(text), text.length());
            TopDocs docs = matches(reader, n);
            return docs;
        } catch (IOException ioe){
            throw new IOException(ioe.getMessage(), ioe);
        } finally {
            reader.close();
        }
    }

    public TopDocs matches(BufferedReader reader, int n)
            throws IOException {
        int totalHitsCount = 0;
        List<Doc> hits = new LinkedList<Doc>();

        int i = 0;
        char c = '\0';
        long offset = 0;    //记录文件偏移;
        LinkedList<Long> offsets = new LinkedList<Long>();  //记录pattern中每个节点的偏移;

        TrieNode transition = _root;
        while (reader.ready() && c!=0xffff){
            offset++;
            c = (char)reader.read();

            /*按段匹配, KMP*/
            if (c=='\n' || c=='\r'){
                while (i>0 && c!=separator.charAt(i)){
                    i = shift[i-1];
                }

                if (c == separator.charAt(i)){
                    i++;
                }

                if (i == separator.length()){
                    i = 0;
                    transition = _root;
                }
                continue;
            }

            i = 0;

            while (!transition.containsTransition(c)
                    && transition!=_root){
                transition = transition.failure();
            }
            if (transition == _root && !offsets.isEmpty()){
                offsets.clear();
            }

            if (transition.containsTransition(c)){
                offsets.addFirst(offset);

                transition = transition.getTransition(c);

                String pattern = transition.pattern();
                if (pattern != null){
                    totalHitsCount++;
                    if (totalHitsCount <= n){
                        Doc doc = new Doc(
                                pattern, offsets.get(pattern.length()-1)
                        );
                        hits.add(doc);
                    }
                }
            }
        }
        return new TopDocs(totalHitsCount, hits.toArray(new Doc[hits.size()]));
    }

    public void filter(String text, char block){
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Trie Nodes\n");

        LinkedList<TrieNode> stack = new LinkedList<TrieNode>();
        stack.add(_root);
        while (!stack.isEmpty()){
            TrieNode transition = stack.pollLast();

            TrieNode[] transitions = transition.transitions();
            for (int i=0; i<transitions.length; i++){
                stack.add(transitions[i]);
            }

            char c = transition.getChar();
            while (transition.parent() != null){
                sb.append("|    ");
                transition = transition.parent();
            }

            sb.append("|----" + c + "\n");
        }
        return sb.toString();
    }

    /*Trie树节点*/
    private class TrieNode {
        /*该节点所代表的的字符*/
        private char  _char;

        /*该节点所匹配的模式，没有则为null*/
        private String _pattern;

        /*父节点*/
        private TrieNode _parent;

        /*失败跳转节点*/
        private TrieNode _failure;

        /*子节点*/
        private Hashtable<Character, TrieNode> _transitions;

        public TrieNode(char c, TrieNode parent){
            _char = c;
            _pattern = null;
            _failure = null;
            _parent = parent;
            _transitions = new Hashtable<Character, TrieNode>();
        }

        public char getChar(){
            return _char;
        }

        public String pattern(String pattern){
            _pattern = pattern;
            return _pattern;
        }

        public String pattern(){
            return _pattern;
        }

        public TrieNode parent(){
            return _parent;
        }

        public TrieNode failure(TrieNode failure){
            _failure = failure;
            return _failure;
        }

        public TrieNode failure(){
            return _failure;
        }

        public TrieNode getTransition(char c){
            return _transitions.get(c);
        }

        public void insertTransition(TrieNode transition){
            _transitions.put(transition.getChar(), transition);
        }

        public boolean containsTransition(char c){
            return _transitions.containsKey(c);
        }

        public TrieNode[] transitions(){
            TrieNode[] transitions = new TrieNode[_transitions.size()];

            int i=0;
            Iterator<TrieNode> iter = _transitions.values().iterator();
            while (iter.hasNext()){
                transitions[i++] = iter.next();
            }

            return transitions;
        }
    }
}
