package com.wisedu.tShow.tools.search.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-10
 * Time: 下午6:17
 * To change this template use File | Settings | File Templates.
 */
public class Searcher {
    /**
     * 从索引文件中查找
     * @param indexPath 索引文件路径
     * @param field 带查找的字段
     * @param queryStr 待查找的关键词
     * @param queries 批量查找
     */
    public static void search(String indexPath, String field, String queryStr, String queries)
            throws IOException, ParseException {
        Date start = new Date();

        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(indexPath)));
        IndexSearcher searcher = new IndexSearcher(reader);

        BufferedReader in;
        if (queries != null){
            in = new BufferedReader(new InputStreamReader(new FileInputStream(queries), "utf-8"));
        } else {
            in = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
        }

        /*分词器,统一成小写*/
        /*Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);*/
        Analyzer analyzer = new IKAnalyzer(true);
        QueryParser parser = new QueryParser(Version.LUCENE_47, field, analyzer);
        /*QueryParser parser = new ComplexPhraseQueryParser(Version.LUCENE_47, field, analyzer);*/
        while (true){
            if (queries==null && queryStr==null){
                System.out.println("Enter query: ");
            }

            String line = (queryStr != null)? queryStr: in.readLine();
            if (line==null || line.length()==-1){
                break;
            }

            line = line.trim();
            if (line.length()==0){
                break;
            }


            BooleanQuery.setMaxClauseCount(2048);
            BooleanQuery boolQuery = new BooleanQuery();
            while (line!=null){
                TermQuery termQuery = new TermQuery(new Term(field, line.toLowerCase()));
                /**
                 * BooleanClause.Occur.MUST         必须包含，类似于逻辑与;
                 * BooleanClause.Occur.MUST_NOT    必须不包含，类似于逻辑非;
                 * BooleanClause.Occur.SHOULD       可以包含，类似于逻辑或;
                 */
                boolQuery.add(termQuery, BooleanClause.Occur.SHOULD);
                line = in.readLine();
            }

            /*Query query = parser.parse(sb.toString());
            doPaginationSearch(in, searcher, query, 10, queries==null&&queryStr==null);*/

            TopDocs results = searcher.search(boolQuery, 50);
            ScoreDoc[] hits = results.scoreDocs;
            /*高亮显示摘要及结果关键词*/
            Highlighter highlighter = new Highlighter(new QueryScorer(boolQuery));
            highlighter.setTextFragmenter(new SimpleFragmenter(100));
            for (int i=0; i<hits.length; i++){
                try {
                    Document document = searcher.doc(hits[i].doc);
                    String path = document.get("path");
                    String text = document.get("content");
                    String summary = highlighter.getBestFragment(analyzer, "content", text);
                    System.out.println(path + ": " + summary);
                } catch (InvalidTokenOffsetsException itoe){
                    throw new RuntimeException(itoe.getMessage(), itoe);
                }
            }

            if (queryStr != null){
                break;
            }
        }

        reader.close();

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime() + " total milliseconds");
    }

    private static void doPaginationSearch(
            BufferedReader in, IndexSearcher searcher, Query query, int hitsPerPage, boolean interactive) throws IOException{
        TopDocs results = searcher.search(query, 5*hitsPerPage);

        ScoreDoc[] hits = results.scoreDocs;
        int numTotalHits = results.totalHits;
        int start = 0;
        int end = Math.min(numTotalHits, hitsPerPage);
        while (true){
            /**
             * hits.length表示Top 5*hitsPerPage
             * results.totalHits表示hits的总count
             */
            if (end > hits.length){
                System.out.println("Only results 1 - " + hits.length +" of " + numTotalHits + " total matching documents collected.");
                System.out.println("Collect more (y/n) ?");
                String line = in.readLine();
                if (line==null || line.charAt(0)=='n'){
                    break;
                }

                hits = searcher.search(query, numTotalHits).scoreDocs;
            }

            end = Math.min(hits.length, start+hitsPerPage);

            for (int i=start; i<end; i++){
                Document doc = searcher.doc(hits[i].doc);
                String path = doc.getField("path").stringValue();
                if (path != null) {
                    System.out.println((i+1) + ". " + path);
                    String content = doc.get("content");
                    if (content != null) {
                        System.out.println("   Content: " + doc.get("content"));
                    }
                } else {
                    System.out.println((i+1) + ". " + "No path for this document");
                }
            }

            if (!interactive || end==0){
                break;
            }

            if (numTotalHits >= end){
                boolean quit = false;
                while (true){
                    System.out.print("Press ");
                    if (start - hitsPerPage >= 0) {
                        System.out.print("(p)revious page, ");
                    }
                    if (start + hitsPerPage < numTotalHits) {
                        System.out.print("(n)ext page, ");
                    }
                    System.out.println("(q)uit or enter number to jump to a page.");

                    String line = in.readLine();
                    if (line==null || line.charAt(0)=='q'){
                        quit = true;
                        break;
                    }
                    if (line.charAt(0)=='p'){
                        start = Math.min(0, start-hitsPerPage);
                        break;
                    } else if (line.charAt(0)=='n'){
                        start = Math.min(start+hitsPerPage, numTotalHits);
                        break;
                    } else {
                        int page = Integer.parseInt(line);
                        if ((page - 1) * hitsPerPage < numTotalHits) {
                            start = (page - 1) * hitsPerPage;
                            break;
                        } else {
                            System.out.println("No such page");
                        }
                    }
                }
                if (quit){
                    break;
                }
                end = Math.min(numTotalHits, start+hitsPerPage);
            }
        }
    }
}
