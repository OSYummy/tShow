package com.wisedu.wechat4j.conf;

public final class ConfigurationBuilder {
    private ConfigurationBase conf = new PropertyConfiguration();

    public ConfigurationBuilder setDebug(boolean debug){
        checkNotBuilt();
        conf.setDebug(debug);
        return this;
    }

    public ConfigurationBuilder setGZIPEnabled(boolean gzipEnabled){
        checkNotBuilt();
        conf.setGZIPEnabled(gzipEnabled);
        return this;
    }

    public ConfigurationBuilder setLoggerFactory(String loggerFactory){
        checkNotBuilt();
        conf.setLoggerFactory(loggerFactory);
        return this;
    }

    public ConfigurationBuilder setOAuthToken(String oAuthToken){
        checkNotBuilt();
        conf.setOAuthToken(oAuthToken);
        return this;
    }

    public ConfigurationBuilder setOAuthAppId(String oAuthAppId){
        checkNotBuilt();
        conf.setOAuthAppID(oAuthAppId);
        return this;
    }

    public ConfigurationBuilder setOAuthAppSecret(String oAuthAppSecret){
        checkNotBuilt();
        conf.setOAuthAppSecret(oAuthAppSecret);
        return this;
    }

    public ConfigurationBuilder setHttpRetryCount(int httpRetryCount){
        checkNotBuilt();
        conf.setHttpRetryCount(httpRetryCount);
        return this;
    }

    public ConfigurationBuilder setHttpReadTimeout(int httpReadTimeout){
        checkNotBuilt();
        conf.setHttpReadTimeout(httpReadTimeout);
        return this;
    }

    public ConfigurationBuilder setHttpConnectionTimeout(int httpConnectionTimeout){
        checkNotBuilt();
        conf.setHttpConnectionTimeout(httpConnectionTimeout);
        return this;
    }

    public ConfigurationBuilder setHttpRetryIntervalSeconds(int httpRetryIntervalSeconds){
        checkNotBuilt();
        conf.setHttpRetryIntervalSeconds(httpRetryIntervalSeconds);
        return this;
    }

    public ConfigurationBuilder setHttpMaxTotalConnections(int httpMaxTotalConnections){
        checkNotBuilt();
        conf.setHttpMaxTotalConnections(httpMaxTotalConnections);
        return this;
    }

    public ConfigurationBuilder setRestBaseURL(String restBaseURL){
        checkNotBuilt();
        conf.setRestBaseURL(restBaseURL);
        return this;
    }

    public ConfigurationBuilder setMediaBaseURL(String mediaBaseURL){
        checkNotBuilt();
        conf.setMediaBaseURL(mediaBaseURL);
        return this;
    }

    public ConfigurationBuilder setOAuthAccessTokenURL(String oAuthAccessTokenURL){
        checkNotBuilt();
        conf.setOAuthAccessTokenURL(oAuthAccessTokenURL);
        return this;
    }

    private void checkNotBuilt(){
        if (conf == null){
            throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
        }
    }

    public Configuration build(){
        checkNotBuilt();
        try {
            return conf;
        } finally {
            conf = null;
        }
    }
}
