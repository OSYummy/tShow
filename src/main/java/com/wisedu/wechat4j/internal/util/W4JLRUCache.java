package com.wisedu.wechat4j.internal.util;

import java.util.LinkedHashMap;
import java.util.Map;

public final class W4JLRUCache<K, V> {
    private Map<K, V> cacheMap;

    public W4JLRUCache(final int maxSize){
        cacheMap = new LinkedHashMap<K, V>(maxSize, 0.75f, true){
            @Override protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
                return size() > maxSize;
            };
        };
    }

    public synchronized void put(K key, V value) {
        cacheMap.put(key, value);
    }

    public synchronized V get(K key){
        return cacheMap.get(key);
    }
}
