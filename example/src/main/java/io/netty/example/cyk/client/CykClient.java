package io.netty.example.cyk.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.cyk.client.decoder.ClientFrameDecoder;
import io.netty.example.cyk.client.decoder.ClientProtocolDecoder;
import io.netty.example.cyk.client.encoder.ClientFrameEncoder;
import io.netty.example.cyk.client.encoder.ClientProtocolEncoder;
import io.netty.example.cyk.dto.Message;
import io.netty.example.cyk.server.BusinessHandler;
import io.netty.example.cyk.util.IdUtil;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;

public class CykClient {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new ClientProtocolEncoder());
                pipeline.addLast(new ClientFrameEncoder());

                pipeline.addLast(new CallbackDispatchHandler());

                pipeline.addLast(new ClientFrameDecoder());
                pipeline.addLast(new ClientProtocolDecoder());
            }
        });

        Message message = new Message();
        Long callId = IdUtil.nextId();
        message.setCallId(callId);
        message.setContent("hello,brother");

        ChannelFuture future = bootstrap.connect("127.0.0.1", 8081);
        future.sync();
        future.channel().writeAndFlush(message);

        MessageCallbackFuture<Message> callbackFuture = new MessageCallbackFuture<Message>();
        DispatchService.registerFuture(callId, callbackFuture);

        //这里会阻塞到结果返回
        Message messageResponse = callbackFuture.get();

        System.out.println(messageResponse.getContent());
    }
}
