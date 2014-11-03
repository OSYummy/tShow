package com.wisedu.wechat4j.conf;

import java.util.Map;

public interface HttpClientConfiguration {
    Map<String, String> getRequestHeaders();

    int getHttpRetryCount();

    int getHttpReadTimeout();

    int getHttpConnectionTimeout();

    int getHttpRetryIntervalSeconds();

    int getHttpMaxTotalConnections();

    boolean isGZIPEnabled();
}
