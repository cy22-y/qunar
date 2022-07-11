package org.example.question5.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.question5.handler.SocketClientInitializer;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SocketClient {

    private int port;
    private String host;

    private Executor executor = Executors.newSingleThreadExecutor();

    public SocketClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void run() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new SocketClientInitializer());
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();

            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String msg = sc.nextLine();
                channel.writeAndFlush(msg);
            }
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        SocketClient client = new SocketClient(7788, "127.0.0.1");
        client.run();
    }

}
