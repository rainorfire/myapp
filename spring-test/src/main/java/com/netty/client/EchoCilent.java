package com.netty.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoCilent {
	
	private final String host;
    private final int port;

    public EchoCilent(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();                //1
            b.group(group)                                //2
             .channel(NioSocketChannel.class)            //3
             .remoteAddress(new InetSocketAddress(host, port))    //4
             .handler(new ChannelInitializer<SocketChannel>() {    //5
                 @Override
                 public void initChannel(SocketChannel ch) 
                     throws Exception {
                     ch.pipeline().addLast(
                             new EchoClientHandler());
                 }
             });

            ChannelFuture f = b.connect().sync();        //6

            f.channel().closeFuture().sync();            //7
        } finally {
            group.shutdownGracefully().sync();            //8
        }
    }

	public static void main(String[] args) {
		EchoCilent cilent = new EchoCilent("127.0.0.1", 8891);
		try {
			cilent.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
