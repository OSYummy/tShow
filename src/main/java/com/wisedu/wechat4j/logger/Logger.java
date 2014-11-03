package com.wisedu.wechat4j.logger;

import com.wisedu.wechat4j.conf.Configuration;
import com.wisedu.wechat4j.conf.ConfigurationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Logger {
    private static final LoggerFactory LOGGER_FACTORY;
    private static final String LOGGER_FACTORY_IMPLEMENTION = "wechat4j.LoggerFactory";

    static {
        LoggerFactory loggerFactory = null;
        String loggerFactoryImpl = System.getProperty(LOGGER_FACTORY_IMPLEMENTION);
        if (loggerFactoryImpl != null){
            loggerFactory = getLoggerFactoryIfAvailable(loggerFactoryImpl, loggerFactoryImpl);
        }

        Configuration conf = ConfigurationContext.getInstance();
        // loggerFactory=com.wisedu.wechat4j.logger.StdOutLoggerFactory
        loggerFactoryImpl = conf.getLoggerFactory();
        if (loggerFactoryImpl != null){
            loggerFactory = getLoggerFactoryIfAvailable(loggerFactoryImpl, loggerFactoryImpl);
        }

        // use SLF4J if it's found in the classpath
        if (null == loggerFactory){
            loggerFactory = getLoggerFactoryIfAvailable("org.slf4j.impl.StaticLoggerBinder", "com.wiesdu.wechat4j.logger.SLF4JLoggerFactory");
        }
        // otherwise, use commons-logging if it's found in the classpath
        if (null == loggerFactory){
            loggerFactory = getLoggerFactoryIfAvailable("org.apache.commons.logging.Log", "com.wiesdu.wechat4j.logger.CommonsLoggingLoggerFactory");
        }
        // otherwise, use log4j if it's found in the classpath
        if (null == loggerFactory){
            loggerFactory = getLoggerFactoryIfAvailable("org.apache.log4j.Logger", "com.wiesdu.wechat4j.logger.Log4JLoggerFactory");
        }
        // otherwise, use the default logger
        if (null == loggerFactory){
            loggerFactory = new StdOutLoggerFactory();
        }

        LOGGER_FACTORY = loggerFactory;
    }

    private static LoggerFactory getLoggerFactoryIfAvailable(String checkClassName, String implementationClass){
        try {
            Class.forName(checkClassName);
            return (LoggerFactory)Class.forName(implementationClass).newInstance();
        } catch (ClassNotFoundException cnfe){

        } catch (InstantiationException ie){
            throw new AssertionError(ie);
        } catch (IllegalAccessException iae){
            throw new AssertionError(iae);
        }
        return null;
    }

    /**
     * Returns a logger instance associated with the specified class.
     *
     * @param clazz class
     * @return logger instance
     */
    public static Logger getLogger(Class<?> clazz){
        return LOGGER_FACTORY.getLogger(clazz);
    }

    /**
     * test if debug level logging is enabled
     *
     * @return if debug level logging is enabled
     */
    public abstract boolean isDebugEnabled();

    /**
     * test if info level logging is enabled
     *
     * @return if info level logging is enabled
     */
    public abstract boolean isInfoEnabled();

    /**
     * test if warn level logging is enabled
     *
     * @return if warn level logging is enabled
     */
    public abstract boolean isWarnEnabled();

    /**
     * test if error level logging is enabled
     *
     * @return if error level logging is enabled
     */
    public abstract boolean isErrorEnabled();

    /**
     *
     * @param message
     */
    public abstract void debug(String message);

    /**
     *
     * @param message
     */
    public abstract void info(String message);

    /**
     *
     * @param message
     */
    public abstract void warn(String message);

    /**
     *
     * @param message
     */
    public abstract void error(String message);
}
