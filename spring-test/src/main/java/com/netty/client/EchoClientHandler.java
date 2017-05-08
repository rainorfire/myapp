package com.netty.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.netty.User;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		User user = new User();
		user.setAge(27);
		user.setName("CYJ");
		user.setSex(1);
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();  
			ObjectOutputStream sOut = new ObjectOutputStream(out);
			sOut.writeObject(user);
			sOut.flush();
			
			ctx.writeAndFlush(Unpooled.copiedBuffer(out.toByteArray()));
			
			sOut.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//	    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",CharsetUtil.UTF_8));
	}
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx,ByteBuf in) {
	    System.out.println("Client received Server MSG: " + in.toString(CharsetUtil.UTF_8));    //3
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {                    //4
		System.out.println("Client EchoClientHandler has Exception!!!");
	    cause.printStackTrace();
	    ctx.close();
	}

}
