package com.wisedu.tShow.tools.wechat.client;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-8-23
 * Time: 下午1:35
 * To change this template use File | Settings | File Templates.
 */
class TokenExecutor {
    // 重传间隔
    private static final long RETRY_INTERVAL = 5000L;

    // 请求间隔
    private static final long EXPIRE_INTERVAL = 1000L;

    // 同步锁
    private static final Object Lock = new Object();

    private ScheduledExecutorService executor;

    public TokenExecutor(){
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void start(Runnable run){
        executor.scheduleWithFixedDelay(run, 0, EXPIRE_INTERVAL-300, TimeUnit.SECONDS);
    }

    public void shutdown(){
        executor.shutdownNow();
    }

    // 这里是否存在死锁？
    public void setAccessToken(){
        synchronized (Lock){
        }
    }

    public String getAccessToken(){
        synchronized (Lock){
        }
        return null;
    }
}
