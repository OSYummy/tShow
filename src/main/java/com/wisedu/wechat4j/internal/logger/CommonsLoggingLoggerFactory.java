package com.wisedu.wechat4j.internal.logger;

final class CommonsLoggingLoggerFactory extends LoggerFactory{

    @Override public Logger getLogger(Class<?> clazz) {
        return new CommonsLoggingLogger(org.apache.commons.logging.LogFactory.getLog(clazz));
    }
}
