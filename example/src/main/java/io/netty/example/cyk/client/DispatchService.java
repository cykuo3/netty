package io.netty.example.cyk.client;

import io.netty.example.cyk.dto.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DispatchService {

    private static final Map<Long, MessageCallbackFuture<Message>> map =
            new ConcurrentHashMap<Long, MessageCallbackFuture<Message>>();

    public static void registerFuture(Long callId, MessageCallbackFuture<Message> promise) {
        map.put(callId, promise);
    }

    public static void callback(Long callId, Message msg) {
        if (map.containsKey(callId)) {
            map.get(callId).setSuccess(msg);
            map.remove(callId);
        }
    }
}
