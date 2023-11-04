package io.netty.example.cyk.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.cyk.server.decoder.ServerFrameDecoder;
import io.netty.example.cyk.server.decoder.ServerProtocolDecoder;
import io.netty.example.cyk.server.encoder.ServerFrameEncoder;
import io.netty.example.cyk.server.encoder.ServerProtocolEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class CykServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ServerFrameDecoder());
                pipeline.addLast(new ServerProtocolDecoder());
                pipeline.addLast(new BusinessHandler());
                pipeline.addLast(new ServerProtocolEncoder());
                pipeline.addLast(new ServerFrameEncoder());
            }
        });

        ChannelFuture channelFuture = serverBootstrap.bind("127.0.0.1", 8081);
        channelFuture.sync();
    }
}
