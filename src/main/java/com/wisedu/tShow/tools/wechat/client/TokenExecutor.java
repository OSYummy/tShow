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
    private static final long EXPIRE_INTERVAL = 300L;

    private ScheduledExecutorService executor;

    public TokenExecutor(){
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void start(Runnable run){
        executor.scheduleWithFixedDelay(run, 0, EXPIRE_INTERVAL, TimeUnit.SECONDS);
    }

    public void shutdown(){
        executor.shutdownNow();
    }
}
