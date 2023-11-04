package io.netty.example.cyk.server.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.cyk.dto.Message;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class ServerProtocolEncoder extends MessageToMessageEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        msg.toBytebuf(byteBuf);
        out.add(byteBuf);
    }
}
