package org.example.question5.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class SocketClientInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline().addLast("StringDecoder", new StringDecoder());
        ch.pipeline().addLast("StringEncoder", new StringEncoder());
        ch.pipeline().addLast("SocketClientHandler", new SocketClientHandler());
    }
}
