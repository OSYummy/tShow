package com.wisedu.tShow.tools.search.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-5
 * Time: 下午7:23
 * To change this template use File | Settings | File Templates.
 */
public class Indexer {
    /**
     * 对dataPath目录下的文件进行索引
     * @param indexPath 索引文件目录
     * @param dataPath 被索引文档目录
     */
    public void index(String indexPath, String dataPath, boolean create)
            throws IOException {
        Date start = new Date();

        Directory indexDir = FSDirectory.open(new File(indexPath));

        /*词法分析器,支持中文，目前貌似是在一元上*/
        /*Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);*/
        Analyzer analyzer = new IKAnalyzer(true);

        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_47, analyzer);
        if (create){
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        } else {
            /*不必删除原有的索引文件*/
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }
        /**
         * 设为true的话，.fdt,.fdx,.fnm,frq,.nrm,.prx,.tii,.tis等文件会被打包为一个.cfs文件
         * <a href="http://lucene.apache.org/core/4_7_0/core/org/apache/lucene/store/CompoundFileDirectory.html"></a>
         * */
        iwc.setUseCompoundFile(false);

        IndexWriter writer = new IndexWriter(indexDir, iwc);

        final File dataDir = new File(dataPath);
        indexDocs(dataDir, writer);

        writer.close();

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime() + " total milliseconds");
    }

    /**
     * 深度遍历file下的文件
     * @param file 带索引文件或目录
     * @param writer
     */
    private void indexDocs(File file, IndexWriter writer)
            throws IOException{
        if (file.canRead()){
            if (file.isDirectory()){
                String[] files = file.list();
                for (int i=0; i<files.length; i++){
                    indexDocs(new File(file, files[i]), writer);
                }
            } else {
                FileInputStream fis = null;

                try {
                    Document doc = new Document();

                    doc.add(new StringField("path", file.getPath(), Field.Store.YES));

                    doc.add(new LongField("modified", file.lastModified(), Field.Store.YES));

                    fis = new FileInputStream(file);

                    StringBuffer sb = new StringBuffer();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"));
                    for (String line = reader.readLine(); line!=null;){
                        sb.append(line);
                        line = reader.readLine();
                    }
                    doc.add(new TextField("content", sb.toString(), Field.Store.YES));

                    /*doc.add(new TextField("content", new InputStreamReader(fis, "utf-8")));*/

                    if (writer.getConfig().getOpenMode().equals(
                            IndexWriterConfig.OpenMode.CREATE
                    )){
                        System.out.println("create file...");
                    } else {
                        System.out.println("update file...");
                    }
                    writer.addDocument(doc);
                } catch (IOException ioe){
                    throw new IOException(ioe.getMessage(), ioe);
                } finally {
                    if (!(fis==null)){
                        fis.close();
                    }
                }
            }
        }
    }
}
