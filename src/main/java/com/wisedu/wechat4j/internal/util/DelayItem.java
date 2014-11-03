package com.wisedu.wechat4j.internal.util;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DelayItem<T> implements Delayed{
    private T item;
    private long expires;
    private long sequence;

    private static AtomicLong sequenceGenerator = new AtomicLong(0);

    public DelayItem(T item, long timeout) {
        this.item = item;
        this.expires = System.nanoTime() + timeout;
        this.sequence = sequenceGenerator.getAndIncrement();
    }

    public T getItem(){
        return item;
    }

    @Override public long getDelay(TimeUnit unit) {
        return unit.convert(expires-System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override public int compareTo(Delayed o) {
        if (this == o) return 0;
        if (o == null)
            throw new NullPointerException("Delayed can not be null");

        if (o instanceof DelayItem){
            DelayItem<T> that = (DelayItem<T>)o;

            long dist = this.expires - that.expires;
            if (dist > 0)
                return 1;
            else if (dist < 0)
                return -1;
            return (this.sequence > that.sequence)? 1: -1;
        } else {
            long dist = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
            return dist>0? 1: (dist<0? -1: 0);
        }
    }
}
