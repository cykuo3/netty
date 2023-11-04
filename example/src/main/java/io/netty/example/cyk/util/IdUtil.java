package io.netty.example.cyk.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdUtil {

    private static final AtomicLong aLong = new AtomicLong();

    public static Long nextId(){
        return aLong.incrementAndGet();
    }
}
