package com.wisedu.wechat4j.internal.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Cache<K, V> {
    private DelayQueue<DelayItem<Pair<K, V>>> delayQueue
            = new DelayQueue<DelayItem<Pair<K, V>>>();
    private ConcurrentHashMap<K, DelayItem<Pair<K, V>>> instanceMap
            = new ConcurrentHashMap<K, DelayItem<Pair<K, V>>>();

    public Cache(){
        Thread daemon = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    timeoutCheck();
                } catch (InterruptedException ite){
                    ite.printStackTrace();
                }
            }
        }, "timeoutCheck");
        daemon.setDaemon(true);
        daemon.start();
    }

    private void timeoutCheck() throws InterruptedException{
        DelayItem<Pair<K, V>> item = delayQueue.take();
        if (item != null){
            instanceMap.remove(item.getItem().getKey());
        }
    }

    public void put(K key, V value, long timeout, TimeUnit unit){
        DelayItem<Pair<K, V>> item = instanceMap.get(key);
        if (item != null){
            delayQueue.remove(item);
        } else {
            item = new DelayItem<Pair<K, V>>(
                    new Pair<K, V>(key, value),
                    TimeUnit.NANOSECONDS.convert(timeout, unit)
            );
        }
        delayQueue.put(item);
        instanceMap.put(key, item);
    }

    public void remove(K key){
        DelayItem<Pair<K, V>> item = instanceMap.remove(key);
        if (key != null){
            delayQueue.remove(item);
        }
    }

    public V getItem(K key){
        DelayItem<Pair<K, V>> item = instanceMap.get(key);
        if (item != null){
            return item.getItem().getValue();
        }
        return null;
    }

    public static void main(String[] args){
        Cache<String, String> cache = new Cache<String, String>();

        cache.put("no.1", "value", 10, TimeUnit.SECONDS);

        System.out.println(cache.getItem("no.1"));
        try {
            Thread.sleep(11000);
        } catch (InterruptedException ite){
            ite.printStackTrace();
        }
        System.out.println(cache.getItem("no.1"));
    }
}
