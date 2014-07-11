package com.wisedu.tShow.tools.dirty;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-14
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public class WuManber {
    /*size of block*/
    private int _mBlock;

    /* minmum length of patterns*/
    private int _mMin;

    /*pattern表*/
    private ArrayList<String> _patterns;

    /*shift表*/
    private HashMap<String, Integer> _shift;

    /*前缀表*/
    private HashMap<String, LinkedList<String>> _prefix;

    /*hash表*/
    private HashMap<String, LinkedList<Integer>> _hash;

    public WuManber(){
        this(1, 1);
    }

    public WuManber(int mMin, int mBlock){
        _mMin = mMin;
        _mBlock = mBlock;
        _patterns = new ArrayList<String>();
        _shift = new HashMap<String, Integer>();
        _prefix = new HashMap<String, LinkedList<String>>();
        _hash = new HashMap<String, LinkedList<Integer>>();
    }


    public void init(File file) throws IOException{
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

            String  line;
            int index = 0;
            while ((line=reader.readLine()) != null){
                _patterns.add(index++, line.toLowerCase());
                /*caculate the minmum pattern length*/
                if (_mMin > line.length()){
                    _mMin = line.length();
                }
            }

            if (_mBlock > _mMin){
                _mMin = _mBlock;
            }

            /*构建表*/
            build();
        } catch (IOException ioe){
            throw new IOException(ioe.getMessage(), ioe);
        } finally {
            if (reader != null){
                reader.close();
            }
        }
    }

    /**
     * 初始化Wu-Manber算法所需的表
     * @param patterns 包含屏蔽词的数组
     */
    public void init(String[] patterns){
        for (int i=0; i<patterns.length; i++){
            _patterns.add(i, patterns[i].toLowerCase());
            /*caculate the minmum pattern length*/
            if (_mMin > patterns[i].length()){
                _mMin = patterns[i].length();
            }
        }

        if (_mBlock > _mMin){
            _mBlock = _mMin;
        }

        build();
    }

    private void build(){
        for (int i=0; i<_patterns.size(); i++){
            String pattern = _patterns.get(i);
            for (int index=0; index<_mMin - _mBlock + 1; index++){
                int preLast = index+_mBlock-1;
                int sufLast = _mMin - 1;
                String block = pattern.substring(index, index+_mBlock).toLowerCase();

                /*计算shift表*/
                if (_shift.get(block)==null
                        || _shift.get(block)>sufLast-preLast){
                    _shift.put(block, sufLast-preLast);

                    if (sufLast-preLast == 0){
                        String prefix = pattern.substring(0, _mBlock).toLowerCase();

                        /*计算prefix表*/
                        LinkedList<String> prefixList = _prefix.get(block);
                        if (prefixList==null){
                            prefixList = new LinkedList<String>();
                            _prefix.put(block, prefixList);
                        }
                        prefixList.add(prefix);

                        /*计算hash表*/
                        LinkedList<Integer> hashList = _hash.get(prefix);
                        if (hashList==null){
                            hashList = new LinkedList<Integer>();
                            _hash.put(prefix, hashList);
                        }
                        hashList.add(i);
                    }
                }
            }
        }
    }

    public List<String> search(String text){
        List result = new ArrayList<String>();

        int index = _mMin-1;
        int defaultValue = _mMin - _mBlock+1;
        while (index < text.length()){
            String block = text.substring(index-_mBlock+1, index+1).toLowerCase();
            if (!_shift.containsKey(block)){
                index += defaultValue;
                continue;
            }

            if (_shift.get(block) > 0){
                index += _shift.get(block);
            } else {
                String prefix = text.substring(index-_mMin+1, index-_mMin+_mBlock+1).toLowerCase();
                LinkedList<String> prefixList = _prefix.get(block);
                for (int i=0; i<prefixList.size(); i++){
                    if (!prefixList.get(i).equals(prefix)){
                        continue;
                    }

                    LinkedList<Integer> hashList = _hash.get(prefix);
                    for (int j=0; j<hashList.size(); j++){
                        String pattern = _patterns.get(hashList.get(j));
                        String target = text.substring(index-_mMin+1, index-_mMin+1+pattern.length()).toLowerCase();
                        if (!target.equals(pattern)){
                            continue;
                        }
                        result.add(pattern);
                    }
                }
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();

       try {
           String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
           String patternPath = basePath + "com/wisedu/core/common/dirty/censor";
           String textPath = basePath + "com/wisedu/core/common/dirty/text_wm";
           /*String[] patterns = new String[]{
                   "Computer", "theoretical", "complexity", "theory"
           };*/

           WuManber wb = new WuManber(6, 2);

           wb.init(new File(patternPath));

           BufferedReader reader
                   = new BufferedReader(new InputStreamReader(new FileInputStream(textPath)));

           String line;
           StringBuffer text = new StringBuffer();
           while ((line=reader.readLine())!=null){
               text.append(line);
           }

           List<String> hits = wb.search(text.toString());
           for (String hit: hits){
               System.out.println(hit);
           }
       } catch (IOException ioe){
           ioe.printStackTrace();
       }

        long end = System.currentTimeMillis();
        System.out.println(end - start + " total milliseconds");
    }
}