package io.netty.example.cyk.client.decoder;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 处理粘包半包问题
 */
public class ClientFrameDecoder extends LengthFieldBasedFrameDecoder {
    public ClientFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }
}
