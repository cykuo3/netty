package io.netty.example.cyk.server.encoder;

import io.netty.handler.codec.LengthFieldPrepender;

public class ServerFrameEncoder extends LengthFieldPrepender {

    public ServerFrameEncoder() {
        super(2);
    }
}
