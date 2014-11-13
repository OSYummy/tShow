package com.wisedu.wechat4j.conf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class ConfigurationBase implements Configuration, Serializable {
    private static final long serialVersionUID = -2634104454092194796L;

    private boolean debug;
    private boolean gzipEnabled;
    private String loggerFactory;

    private String oAuthToken;
    private String oAuthAppId;
    private String oAuthAppSecret;
    private String oAuthAccessToken;

    private int httpRetryCount;
    private int httpReadTimeout;
    private int httpConnectionTimeout;
    private int httpRetryIntervalSeconds;
    private int httpMaxTotalConnections;

    private Map<String, String> requestHeaders;
    private String restBaseURL = "https://api.weixin.qq.com/cgi-bin/";
    private String mediaBaseURL = "http://file.api.weixin.qq.com/cgi-bin/media/";
    private String oAuthAccessTokenURL = "https://api.weixin.qq.com/cgi-bin/token";

    protected ConfigurationBase(){
        setDebug(false);
        setGZIPEnabled(true);
        setLoggerFactory(null);

        setOAuthToken(null);
        setOAuthAppID(null);
        setOAuthAppSecret(null);

        setHttpRetryCount(0);
        setHttpReadTimeout(10000);
        setHttpConnectionTimeout(10000);
        setHttpRetryIntervalSeconds(5);
        setHttpMaxTotalConnections(20);
    }

    @Override public final boolean isDebugEnabled(){
        return debug;
    }

    protected final void setDebug(boolean debug){
        this.debug = debug;
    }

    @Override public final boolean isGZIPEnabled(){
        return gzipEnabled;
    }

    protected final void setGZIPEnabled(boolean gzipEnabled){
        this.gzipEnabled = gzipEnabled;
        initRequestHeaders();
    }

    private void initRequestHeaders(){
        requestHeaders = new HashMap<String, String>();
        if (isGZIPEnabled()){
            requestHeaders.put("Accept-Encoding", "gzip");
        }
    }

    public final Map<String, String> getRequestHeaders(){
        return requestHeaders;
    }

    @Override public final String getLoggerFactory(){
        return loggerFactory;
    }

    protected final void setLoggerFactory(String loggerFactory){
        this.loggerFactory = loggerFactory;
    }

    @Override public final String getOAuthToken(){
        return oAuthToken;
    }

    protected final void setOAuthToken(String oAuthToken){
        this.oAuthToken = oAuthToken;
    }

    @Override public final String getOAuthAppID(){
        return oAuthAppId;
    }

    protected final void setOAuthAppID(String oAuthToken){
        this.oAuthAppId = oAuthToken;
    }

    @Override public final String getOAuthAppSecret(){
        return oAuthAppSecret;
    }

    protected final void setOAuthAppSecret(String oAuthAppSecret){
        this.oAuthAppSecret = oAuthAppSecret;
    }

    @Override public final String getOAuthAccessToken() {
        return oAuthAccessToken;
    }

    protected final void setOAuthAccessToken(String oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
    }

    @Override public final int getHttpRetryCount(){
        return httpRetryCount;
    }

    protected final void setHttpRetryCount(int httpRetryCount){
        this.httpRetryCount = httpRetryCount;
    }

    @Override public final int getHttpReadTimeout(){
        return httpReadTimeout;
    }

    protected final void setHttpReadTimeout(int httpReadTimeout){
        this.httpReadTimeout = httpReadTimeout;
    }

    @Override public final int getHttpConnectionTimeout(){
        return httpConnectionTimeout;
    }

    protected final void setHttpConnectionTimeout(int httpConnectionTimeout){
        this.httpConnectionTimeout = httpConnectionTimeout;
    }

    @Override public final int getHttpRetryIntervalSeconds(){
        return httpRetryIntervalSeconds;
    }

    protected final void setHttpRetryIntervalSeconds(int httpRetryIntervalSeconds){
        this.httpRetryIntervalSeconds = httpRetryIntervalSeconds;
    }

    @Override public final int getHttpMaxTotalConnections(){
        return httpMaxTotalConnections;
    }

    protected final void setHttpMaxTotalConnections(int httpMaxTotalConnections){
        this.httpMaxTotalConnections = httpMaxTotalConnections;
    }

    @Override public String getMediaBaseURL() {
        return mediaBaseURL;
    }

    protected final void setMediaBaseURL(String mediaBaseURL) {
        this.mediaBaseURL = mediaBaseURL;
    }

    @Override public String getRestBaseURL() {
        return restBaseURL;
    }

    protected final void setRestBaseURL(String restBaseURL) {
        this.restBaseURL = restBaseURL;
    }

    @Override public String getOAuthAccessTokenURL(){
        return oAuthAccessTokenURL;
    }

    protected final void setOAuthAccessTokenURL(String oAuthAccessTokenURL){
        this.oAuthAccessTokenURL = oAuthAccessTokenURL;
    }
}