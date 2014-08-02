package com.wisedu.tShow.tools.search.TSAnalyzer.dic;

import com.wisedu.tShow.tools.search.TSAnalyzer.cfg.Configuration;
import com.wisedu.tShow.tools.search.TSAnalyzer.cfg.DefaultConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-17
 * Time: 下午6:39
 * To change this template use File | Settings | File Templates.
 */
public class Dictionary {
    private static Logger log = LoggerFactory.getLogger(Dictionary.class);

    // 同步锁
    private static Object lock = Object.class;

    // 配置
    private Configuration _cfg;

    // 词典单例
    private static Dictionary _singleton;

    // 主词典
    private DicSegment _MainDic;

    private Dictionary() {
        _cfg = new DefaultConfig();
        loadMainDic();
    }

    public static Dictionary getInstance() {
        synchronized (lock){
            if (_singleton == null){
                _singleton = new Dictionary();
            }
        }
        return _singleton;
    }

    /**
     * 加载主词典
     */
    public void loadMainDic() {
        _MainDic = new DicSegment();

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(_cfg.getMainDictionary());
        if (is == null){
            throw new RuntimeException("Fail to found main dictionary");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 512);
            String word = null;
            do {
                word = reader.readLine();
                if (word!=null || !"".equals(word)){
                    _MainDic.insert(word, new Hit());
                }
            } while (word!=null);
        } catch (IOException ioe){
            throw new RuntimeException("Fail to load main dictionary");
        } finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException ioe){
                    throw new RuntimeException("Fail to close input stream");
                }
            }
        }
    }

    /**
     * 批量加载词条
     * @param words
     */
    public void addWords(Collection<String> words){
        if (words != null){
            for (String word: words){
                _MainDic.insert(word, new Hit());
            }
        }
    }

    /**
     * 从主词典中查询
     * @param word
     */
    public List<Hit> matchInMainDic(String word){
        return _MainDic.match(word, 0);
    }
}
