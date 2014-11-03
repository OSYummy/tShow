package com.wisedu.wechat4j.client;

import com.wisedu.wechat4j.auth.Authorization;
import com.wisedu.wechat4j.conf.Configuration;

interface WechatBase {
    /**
     * Returns the authorization scheme for this instance.<br>
     * The returned type will be either of BasicAuthorization, OAuthAuthorization, or NullAuthorization
     *
     * @return the authorization scheme for this instance
     */
    Authorization getAuthorization();

    /**
     * Returns the configuration associated with this instance
     *
     * @return configuration associated with this instance
     * @since Wechat4J 1.0.0
     */
    Configuration getConfiguration();
}
