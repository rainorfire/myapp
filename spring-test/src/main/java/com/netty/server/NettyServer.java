package com.netty.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	
	public static void main(String[] args) {
		NettyServer adapt = new NettyServer();
		try {
			adapt.start(8891);
		} catch (Exception e) {
			e.printStackTrace();
		}      
	}
	
	public void start(int port) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4
             .channel(NioServerSocketChannel.class)        //5
             .localAddress(new InetSocketAddress(port))    //6
             .childHandler(new ChannelInitializer<SocketChannel>() { //7
                 @Override
                 public void initChannel(SocketChannel ch) 
                     throws Exception {
                     ch.pipeline().addLast(
                             new MyChannelInboundHandlerAdapt());
                 }
             });

            ChannelFuture f = b.bind().sync();            //8
            System.out.println(MyChannelInboundHandlerAdapt.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9
        } finally {
            group.shutdownGracefully().sync();            //10
        }
    }
}
