package com.wisedu.wechat4j.conf;

import java.io.*;
import java.util.*;

public final class PropertyConfiguration extends ConfigurationBase implements Serializable {
    private static final long serialVersionUID = -7945487770822598977L;

    // Misc.
    private static final String DEBUG = "debug";
    private static final String GZIPENABLED = "http.gzip";
    private static final String LOGGER_FACTORY = "loggerFactory";

    // OAuth
    private static final String OAUTH_TOKEN = "oauth.token";
    private static final String OAUTH_APPID = "oauth.appId";
    private static final String OAUTH_APPSECRET = "oauth.appSecret";
    private static final String OAUTH_ACCESSTOKEN = "oauth.accessToken";

    // HTTP connection
    private static final String HTTP_RETRY_COUNT = "http.retryCount";
    private static final String HTTP_READ_TIMEOUT = "http.readTimeout";
    private static final String HTTP_CONNECTION_TIMEOUT = "http.connectionTimeout";
    private static final String HTTP_RETER_INTERVAL_SECONDS = "http.retryIntervalMillis";
    private static final String HTTP_MAX_TOTAL_CONNECTIONS = "http.maxTotalConnections";

    // Base URLs
    private static final String REST_BASE_URL = "restBaseURL";
    private static final String MEDIA_BASE_URL = "mediaBaseURL";
    private static final String OAUTH_ACCESSTOKEN_URL = "oAuthAccessTokenURL";

    public PropertyConfiguration(InputStream is){
        super();
        Properties props = new Properties();
        loadProperties(props, is);
        setFields(props);
    }

    public PropertyConfiguration(Properties props){
        super();
        setFields(props);
    }

    public PropertyConfiguration(){
        super();
        Properties props = new Properties();
        try {
            // Specify properties via system properties
            Enumeration enums = System.getProperties().keys();
            while (enums.hasMoreElements()){
                String key = enums.nextElement().toString();
                if (-1 != key.indexOf("wechat4j.")){
                    props.setProperty(key, System.getProperty(key));
                }
            }

            // Specify properties via environment variables
            Map<String, String> envMap = System.getenv();
            for (String key : envMap.keySet()){
                if (-1 != key.indexOf("wechat4j.")){
                    props.setProperty(key, envMap.get(key));
                }
            }
            normalize(props);
        } catch (SecurityException se){

        }

        final String WECHAT4J_PROPERTIES = "wechat4j.properties";

        // override System properties with ./wechat4j.properties in the classpath
        loadProperties(props, getClass().getResource("").getPath() + WECHAT4J_PROPERTIES);
        // then, override with /wechat4j.properties in the classpath
        loadProperties(props, Configuration.class.getResourceAsStream("/" + WECHAT4J_PROPERTIES));

        setFields(props);
    }

    private boolean loadProperties(Properties props, String path){
        FileInputStream fis = null;
        try {
            File file = new File(path);
            if (file.exists() && file.isFile()){
                fis = new FileInputStream(file);
                props.load(fis);
                return true;
            }
        } catch (IOException ioe){

        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException ioe){

                }
            }
        }
        return false;
    }

    private boolean loadProperties(Properties props, InputStream in){
        try {
            if (in != null){
                props.load(in);
                return true;
            }
        } catch (IOException ioe){

        }
        return false;
    }

    private void normalize(Properties props){
        List<String> toBeNormalized = new ArrayList<String>();
        for (Object key: props.keySet()){
            if (-1 != ((String)key).indexOf("wechat4j.")) {
                toBeNormalized.add((String)key);
            }
        }
        for (String key: toBeNormalized){
            String property = (String)props.remove(key);
            int index = key.indexOf("wechat4j.");
            props.setProperty(key.substring(index+9), property);
        }
    }

    private void setFields(Properties props){
        if (notNull(props, DEBUG)){
            setDebug(getBooleanProperty(props, DEBUG));
        }

        if (notNull(props, GZIPENABLED)){
            setGZIPEnabled(getBooleanProperty(props, GZIPENABLED));
        }

        if (notNull(props, LOGGER_FACTORY)){
            setLoggerFactory(getStringProperty(props, LOGGER_FACTORY));
        }

        if (notNull(props, OAUTH_TOKEN)){
            setOAuthToken(getStringProperty(props, OAUTH_TOKEN));
        }

        if (notNull(props, OAUTH_APPID)){
            setOAuthAppId(getStringProperty(props, OAUTH_APPID));
        }

        if (notNull(props, OAUTH_APPSECRET)){
            setOAuthAppSecret(getStringProperty(props, OAUTH_APPSECRET));
        }

        if (notNull(props, OAUTH_ACCESSTOKEN)) {
            setOAuthAccessToken(getStringProperty(props, OAUTH_ACCESSTOKEN));
        }

        if (notNull(props, HTTP_RETRY_COUNT)){
            setHttpRetryCount(getIntProperty(props, HTTP_RETRY_COUNT));
        }

        if (notNull(props, HTTP_READ_TIMEOUT)){
            setHttpReadTimeout(getIntProperty(props, HTTP_READ_TIMEOUT));
        }

        if (notNull(props, HTTP_CONNECTION_TIMEOUT)){
            setHttpConnectionTimeout(getIntProperty(props, HTTP_CONNECTION_TIMEOUT));
        }

        if (notNull(props, HTTP_RETER_INTERVAL_SECONDS)){
            setHttpRetryIntervalSeconds(getIntProperty(props, HTTP_RETER_INTERVAL_SECONDS));
        }

        if (notNull(props, HTTP_MAX_TOTAL_CONNECTIONS)){
            setHttpRetryIntervalSeconds(getIntProperty(props, HTTP_MAX_TOTAL_CONNECTIONS));
        }

        if (notNull(props, REST_BASE_URL)){
            setRestBaseURL(getStringProperty(props, REST_BASE_URL));
        }

        if (notNull(props, MEDIA_BASE_URL)){
            setMediaBaseURL(getStringProperty(props, MEDIA_BASE_URL));
        }

        if (notNull(props, OAUTH_ACCESSTOKEN_URL)){
            setOAuthAccessTokenURL(getStringProperty(props, OAUTH_ACCESSTOKEN_URL));
        }
    }

    private boolean notNull(Properties props,String name){
        return props.getProperty(name) != null;
    }

    private boolean getBooleanProperty(Properties props, String name){
        String value = props.getProperty(name);
        return Boolean.valueOf(value);
    }

    private int getIntProperty(Properties props, String name){
        String value = props.getProperty(name);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe){
            return -1;
        }
    }

    private long getLongProperty(Properties props, String name){
        String value = props.getProperty(name);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe){
            return -1L;
        }
    }

    private String getStringProperty(Properties props, String name){
        return props.getProperty(name);
    }
}
