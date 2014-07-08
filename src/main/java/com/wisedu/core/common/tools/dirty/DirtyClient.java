package com.wisedu.core.common.tools.dirty;

import com.wisedu.core.common.exception.ServiceException;
import com.wisedu.core.common.tools.dirty.doc.TopDocs;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.engine.DefaultObjectSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static OMFactory fac;

    private static OMNamespace omNS;

    public DirtyClient(){
        if (fac==null){
            fac = OMAbstractFactory.getOMFactory();
        }
        omNS = fac.createOMNamespace("", "tns");
    }

    public void setTargetEPR(String erp){
        targetEPR = new EndpointReference(erp);
    }

    /**
     * 脏词匹配
     * @param accessToken 授权令牌
     * @param file 待匹配的文件
     * @param n 命中的前n项
     * @return
     * @throws com.wisedu.core.common.exception.ServiceException
     */
    public TopDocs matches(String accessToken, String file, int n) throws ServiceException {
        /*构建消息*/
        OMElement root = fac.createOMElement("", omNS);

        OMElement omAccessToken = fac.createOMElement("", omNS);
        omAccessToken.setText(accessToken);
        root.addChild(omAccessToken);

        OMElement omFileType = fac.createOMElement("", omNS);
        omFileType.setText(Integer.toString(12));
        root.addChild(omFileType);

        OMElement omHitCount = fac.createOMElement("", omNS);
        omHitCount.setText(Integer.toString(n));
        root.addChild(omHitCount);

        OMElement omFileContent = fac.createOMElement("", omNS);
        omFileContent.setText(file);
        root.addChild(omFileContent);

        /*设置选项，使用MTOM传输*/
        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

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
