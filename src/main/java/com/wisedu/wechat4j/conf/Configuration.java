package com.wisedu.wechat4j.conf;

import java.io.Serializable;

public interface Configuration extends Serializable,
        HttpClientConfiguration,
        AuthorizationConfiguration {

    boolean isDebugEnabled();

    String getLoggerFactory();

    String getRestBaseURL();

    String getMediaBaseURL();
}
