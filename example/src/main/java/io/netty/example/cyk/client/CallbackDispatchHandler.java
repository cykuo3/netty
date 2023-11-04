package io.netty.example.cyk.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.example.cyk.dto.Message;

public class CallbackDispatchHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        DispatchService.callback(msg.getCallId(),msg);
    }
}
