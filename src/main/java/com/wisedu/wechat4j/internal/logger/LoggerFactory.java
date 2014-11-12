package com.wisedu.wechat4j.internal.logger;

public abstract class LoggerFactory {
    /**
     * Returns a logger associated with the specified class.
     *
     * @param clazz class
     * @return a logger instance
     */
    public abstract Logger getLogger(Class<?> clazz);
}
