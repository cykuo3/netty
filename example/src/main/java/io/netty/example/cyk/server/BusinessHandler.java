package io.netty.example.cyk.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.example.cyk.dto.Message;

public class BusinessHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        String content = msg.getContent();
        Long callId = msg.getCallId();

        Message callBackMessage = new Message();
        callBackMessage.setCallId(callId);
        callBackMessage.setContent(String.format("我收到了【%s】,OK", content));

        ctx.writeAndFlush(callBackMessage);
    }
}
