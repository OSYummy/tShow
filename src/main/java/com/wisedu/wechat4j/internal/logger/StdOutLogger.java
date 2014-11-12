package com.wisedu.wechat4j.internal.logger;

import com.wisedu.wechat4j.conf.ConfigurationContext;

import java.util.Date;

final class StdOutLogger extends Logger{
    private static final boolean DEBUG = ConfigurationContext.getInstance().isDebugEnabled();

    @Override public boolean isDebugEnabled() {
        return DEBUG;
    }

    @Override public boolean isInfoEnabled() {
        return true;
    }

    @Override public boolean isWarnEnabled() {
        return true;
    }

    @Override public boolean isErrorEnabled() {
        return true;
    }

    @Override public void debug(String message) {
        if (DEBUG){
            System.out.println("[" + new Date() + "]" + message);
        }
    }

    @Override public void info(String message) {
        System.out.println("[" + new Date() + "]" + message);
    }

    @Override public void warn(String message) {
        System.out.println("[" + new Date() + "]" + message);
    }

    @Override public void error(String message) {
        System.out.println("[" + new Date() + "]" + message);
    }
}
