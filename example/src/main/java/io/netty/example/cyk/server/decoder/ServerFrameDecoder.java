package io.netty.example.cyk.server.decoder;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 处理粘包半包问题
 */
public class ServerFrameDecoder extends LengthFieldBasedFrameDecoder {
    public ServerFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2,
                0, 2);
    }
}
