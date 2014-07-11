package com.wisedu.tShow.service.dirty;

import com.wisedu.tShow.tools.dirty.ACTrie;
import com.wisedu.tShow.tools.dirty.doc.Doc;
import com.wisedu.tShow.tools.dirty.doc.TopDocs;
import com.wisedu.core.common.utils.PropertyConfigurerUtil;
import org.apache.axiom.om.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-8
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class DirtyService {
    private final static Logger log = LoggerFactory.getLogger(DirtyService.class);

    private static OMFactory fac;

    private static OMNamespace omNS;

    public DirtyService(){
        if (fac == null){
            fac = OMAbstractFactory.getOMFactory();
        }
        omNS = fac.createOMNamespace(DirtyConstants.NAMESPACE, "tns");
    }

    /**
     * 脏词匹配
     * @param root 根节点
     */
    public OMElement matches(OMElement root) throws IOException{
        // 解析消息
        OMElement omToken = null;
        OMElement omHitCount = null;
        OMElement omFileContent = null;
        Iterator iter = root.getChildElements();
        while (iter.hasNext()){
            OMElement element = (OMElement)iter.next();
            String localeName = element.getLocalName();

            if (localeName.equals(DirtyConstants.TAG_TOKEN)){
                omToken = element;
            }
            if (localeName.equals(DirtyConstants.TAG_HITCOUNT)){
                omHitCount = element;
            }
            if (localeName.equals(DirtyConstants.TAG_FILECONTENT)){
                omFileContent = element;
            }
        }

        // 获取文本
        BufferedReader reader = null;
        try {
            OMText omText = (OMText)omFileContent.getFirstOMChild();
            DataHandler dataHandler = (DataHandler)omText.getDataHandler();
            reader = new BufferedReader(new InputStreamReader(dataHandler.getInputStream()));
        } catch (IOException ioe){
            log.error(ioe.getMessage(), ioe);
            throw new IOException("fail to read text", ioe);
        }

        // 计算匹配项
        TopDocs topDocs = null;
        try {
            String filePatternPath = (String)PropertyConfigurerUtil.getProperty("service.dirty.patternPath");
            ACTrie trie = new ACTrie(new File(filePatternPath));
            int count = Integer.parseInt(omHitCount.getText());
            if (reader != null){
                topDocs = trie.matches(reader, count);
            }
        } catch (IOException ioe){
            log.error(ioe.getMessage(), ioe);
        }

        // Response
        OMElement response = fac.createOMElement(DirtyConstants.TAG_RESPONSE, omNS);
        OMElement omTopDocs = fac.createOMElement(DirtyConstants.TAG_TOPDOCS, omNS);
        response.addChild(omTopDocs);

        // 匹配总数
        OMElement omTotalCount = fac.createOMElement(DirtyConstants.TAG_TOTALCOUNT, omNS);
        omTotalCount.setText(Integer.toString(topDocs.getTotalHitsCount()));
        response.addChild(omTotalCount);

        // 匹配列表
        OMElement omDocs = fac.createOMElement(DirtyConstants.TAG_DOCS, omNS);
        for (Doc doc: topDocs.getDocs()){
            OMElement omDoc = fac.createOMElement(DirtyConstants.TAG_DOC, omNS);

            // 匹配项
            OMElement omPattern = fac.createOMElement(DirtyConstants.TAG_PATTERN, omNS);
            omDoc.setText(doc.getPattern());
            omDoc.addChild(omPattern);

            // 偏移量
            OMElement omOffset = fac.createOMElement(DirtyConstants.TAG_OFFSET, omNS);
            omOffset.setText(Long.toString(doc.getOffset()));
            omDoc.addChild(omOffset);

            omDocs.addChild(omDoc);
        }
        response.addChild(omDocs);

        return response;
    }
}
