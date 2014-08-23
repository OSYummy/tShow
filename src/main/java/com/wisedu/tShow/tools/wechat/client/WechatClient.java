package com.wisedu.tShow.tools.wechat.client;

import com.wisedu.core.utils.PropertyConfigurerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-8-23
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
public class WechatClient {
    private static final Logger log = LoggerFactory.getLogger(WechatClient.class);

    private String appId;

    private String appSecret;

    private TokenExecutor executor;

    public WechatClient(String appId, String appSecret){
        this.appId = appId;
        this.appSecret = appSecret;
        executor = new TokenExecutor();
    }

    public void close(){
        if (executor != null){
            executor.shutdown();
        }
    }
}
