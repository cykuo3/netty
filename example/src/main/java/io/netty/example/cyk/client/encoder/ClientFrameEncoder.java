package io.netty.example.cyk.client.encoder;

import io.netty.handler.codec.LengthFieldPrepender;

public class ClientFrameEncoder extends LengthFieldPrepender {

    public ClientFrameEncoder() {
        super(2);
    }
}
