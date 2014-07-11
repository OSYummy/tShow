package com.wisedu.tShow.tools.dirty;

import com.wisedu.core.common.exception.ServiceException;
import com.wisedu.tShow.tools.dirty.doc.Doc;
import com.wisedu.tShow.tools.dirty.doc.TopDocs;
import com.wisedu.tShow.service.dirty.DirtyConstants;
import org.apache.axiom.om.*;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.builder.unknowncontent.InputStreamDataSource;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-8
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class DirtyClient {
    private final static Logger log = LoggerFactory.getLogger(DirtyClient.class);

    private static EndpointReference targetEPR;

    private static OMNamespace omNS;

    private static OMFactory fac;

    private static int BUFFER_SIZE = 1024<<3;

    public DirtyClient(){
        if (fac==null){
            fac = OMAbstractFactory.getOMFactory();
        }
        omNS = fac.createOMNamespace(DirtyConstants.NAMESPACE, "tns");
    }

    /**
     * 设置服务目标地址
     * @param erp 服务标的
     */
    public void setTargetEPR(String erp){
        targetEPR = new EndpointReference(erp);
    }

    /**
     * 脏词匹配
     * @param accessToken 授权令牌
     * @param file 待匹配的文件
     * @param n 匹配到脏词的前n项
     */
    public TopDocs matches(String accessToken, File file, int n) throws ServiceException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException fnfe){
            log.error(fnfe.getMessage(), fnfe);
            throw new ServiceException("file not exists", fnfe);
        }

        try {
            return matches(accessToken, fis, n);
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException ioe){
                    log.error(ioe.getMessage(), ioe);
                    throw new ServiceException("fail to close file", ioe);
                }
            }
        }
    }

    public TopDocs matches(String accessToken, String text, int n) throws ServiceException {
        InputStream is = IOUtils.toInputStream(text);

        try {
            return matches(accessToken, is, n);
        } finally {
            try {
                is.close();
            } catch (IOException ioe){
                log.error(ioe.getMessage(), ioe);
                throw new ServiceException("fail to close file", ioe);
            }
        }
    }

    /**
     * 脏词匹配
     * @param accessToken 授权令牌
     * @param is 待匹配的文件流
     * @param n 匹配到脏词的前n项
     */
    public TopDocs matches(String accessToken, InputStream is, int n) throws ServiceException {
        // 构建消息
        OMElement root = fac.createOMElement(DirtyConstants.SERVICE_MATCHES, omNS);

        // 授权令牌
        OMElement omToken = fac.createOMElement(DirtyConstants.TAG_TOKEN, omNS);
        omToken.setText(accessToken);
        root.addChild(omToken);

        // 命中数
        OMElement omHitCount = fac.createOMElement(DirtyConstants.TAG_FILECONTENT, omNS);
        omHitCount.setText(Integer.toString(n));
        root.addChild(omHitCount);

        // 文件
        OMElement omFileContent = fac.createOMElement("", omNS);
        root.addChild(omFileContent);

        DataHandler dataHandler = new DataHandler(new InputStreamDataSource(is));
        OMText omText = fac.createOMText(dataHandler, true);
        omFileContent.addChild(omText);

        // 设置选项
        Options options = new Options();

        // 服务地址
        options.setTo(targetEPR);

        // SO_TIMEOUT in milliseconds, which is the timeout for waiting for data or,
        // put differently, a maximum period inactivity between two consecutive data packets.
        // A timeout value of zero is interpretedas an infinite timeout.
        // fyi: HttpMethodBase.readResponse(HttpState state, HttpConnection conn)
        options.setProperty(HTTPConstants.SO_TIMEOUT, 60000);

        // 使用http协议
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        // 使用MTOM传输

        // 使用MTOM传输文件
        options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);

        ServiceClient client = null;
        try {
            client = new ServiceClient();

            client.setOptions(options);

            // Directly invoke an anonymous operation(OutInAxisOperationClient) with an In-Out MEP.
            // This method sends your supplied XML and receives a response.
            OMElement response = client.sendReceive(root);

            // 解析响应
            OMElement omTopDocs = null;
            Iterator iter = response.getChildElements();
            while (iter.hasNext()){
                OMElement element = (OMElement)iter.next();
                String localeName = element.getLocalName();

                if (localeName.equals(DirtyConstants.TAG_TOPDOCS)){
                    omTopDocs = element;
                }
            }

            OMElement omDocs = null;
            OMElement omTotalCount = null;
            iter = omTopDocs.getChildElements();
            while (iter.hasNext()){
                OMElement element = (OMElement)iter.next();
                String localName = element.getLocalName();

                if (localName.equals(DirtyConstants.TAG_DOCS)){
                    omDocs = element;
                }
                if (localName.equals(DirtyConstants.TAG_TOTALCOUNT)){
                    omTotalCount = element;
                }
            }

            List omDocList = new ArrayList();
            iter = omDocs.getChildElements();
            while (iter.hasNext()){
                OMElement element = (OMElement)iter.next();
                String localName = element.getLocalName();
                if (localName.equals(DirtyConstants.TAG_DOC)){
                    omDocList.add(element);
                }
            }

            List<Doc> docList = new ArrayList<Doc>();
            for (int i=0; i<omDocList.size(); i++){
                OMElement omDoc = (OMElement)omDocList.get(i);

                OMElement omPattern = null;
                OMElement omOffset = null;
                iter = omDoc.getChildElements();
                while (iter.hasNext()){
                    OMElement element = (OMElement)iter.next();
                    String localName = element.getLocalName();

                    if (localName.equals(DirtyConstants.TAG_PATTERN)){
                        omPattern = element;
                    }
                    if (localName.equals(DirtyConstants.TAG_OFFSET)){
                        omOffset = element;
                    }
                }
                docList.add(new Doc(omPattern.getText(), Integer.parseInt(omOffset.getText())));
            }

            TopDocs topDocs = new TopDocs(
                    Integer.parseInt(omTotalCount.getText()), docList.toArray(new Doc[docList.size()])
            );
            return topDocs;
        } catch (AxisFault asf){
            log.error(asf.getMessage(), asf);
            throw new ServiceException("fail to match", asf);
        } finally {
            if (client!=null){
                try {
                    // Release resources allocated by the transport during the last service invocation.
                    client.cleanupTransport();
                } catch (AxisFault asf){
                    log.error(asf.getMessage(), asf);
                    throw new ServiceException("fail to close the client", asf);
                }
            }
        }
    }
}
