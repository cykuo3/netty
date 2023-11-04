package io.netty.example.cyk.server.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.cyk.dto.Message;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 处理协议问题
 */
public class ServerProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        Message message = new Message();
        message.fromBytebuf(msg);
        out.add(message);
    }
}
