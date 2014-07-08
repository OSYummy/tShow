package com.wisedu.core.common.tools.dirty;

import com.wisedu.core.common.exception.ServiceException;
import com.wisedu.core.common.tools.dirty.doc.TopDocs;
import com.wisedu.tShow.service.dirty.DirtyConstants;
import org.apache.axiom.om.*;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.io.File;
import java.util.Iterator;

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
     * @param file 待匹配的文件地址
     * @param n 匹配到脏词的前n项
     */
    public TopDocs matches(String accessToken, File file, int n) throws ServiceException {
        // 构建消息
        OMElement root = fac.createOMElement(DirtyConstants.SERVICE_MATCHES, omNS);

        // 授权令牌
        OMElement omToken = fac.createOMElement(DirtyConstants.TAG_TOKEN, omNS);
        omToken.setText(accessToken);
        root.addChild(omToken);

        // 命中数
        OMElement omHitCount = fac.createOMElement(DirtyConstants.TAG_HITCOUNT, omNS);
        omHitCount.setText(Integer.toString(n));
        root.addChild(omHitCount);

        // 文件
        OMElement omFileContent = fac.createOMElement("", omNS);
        root.addChild(omFileContent);

        DataHandler dataHandler = new DataHandler(new FileDataSource(file));
        OMText omText = fac.createOMText(dataHandler, true);
        omFileContent.addChild(omText);

        // 设置选项
        Options options = new Options();

        // 服务地址
        options.setTo(targetEPR);

        // 使用http协议
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        // SO_TIMEOUT in milliseconds, which is the timeout for waiting for data or,
        // put differently, a maximum period inactivity between two consecutive data packets.
        // A timeout value of zero is interpretedas an infinite timeout.
        // fyi: HttpMethodBase.readResponse(HttpState state, HttpConnection conn)
        options.setProperty(HTTPConstants.SO_TIMEOUT, 60000);

        // 使用MTOM传输
        options.setProperty(Constants.Configuration.ENABLE_MTOM, true);

        TopDocs docs = null;
        ServiceClient client = null;
        try {
            client = new ServiceClient();

            client.setOptions(options);

            OMElement response = client.sendReceive(root);

            OMElement omDocs = null;
            Iterator iter = response.getChildElements();
            while (iter.hasNext()){
                OMElement element = (OMElement)iter.next();
                String localeName = element.getLocalName();
                if (localeName.equals("")){
                    omDocs = element.getFirstElement();
                }
            }
        } catch (AxisFault asf){
            log.error(asf.getMessage(), asf);
            throw new ServiceException("fail to match", asf);
        } finally {
            if (client != null){
                try {
                    client.cleanup();
                } catch (AxisFault asf){
                    log.error(asf.getMessage(), asf);
                    throw new ServiceException("fail to close the client", asf);
                }
            }
        }

        return docs;
    }
}
