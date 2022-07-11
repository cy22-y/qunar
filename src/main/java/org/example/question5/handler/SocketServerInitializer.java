package org.example.question5.handler;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class SocketServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        //加入一个节码器
        ch.pipeline().addLast("StringDecoder", new StringDecoder());
        //加入一个编码器
        ch.pipeline().addLast("StringEncoder", new StringEncoder());
        ch.pipeline().addLast("TextWebSocketFrameHandler", new MsgServerSocketHandler());
    }
}
