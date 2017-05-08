package com.netty.server;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.netty.User;
import com.test.util.JsonUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MyChannelInboundHandlerAdapt extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		ByteBuf in = (ByteBuf) msg;
//        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));    
//		ctx.write(in);
		
		ByteBuf bbMsg = (ByteBuf) msg;
		if(bbMsg.hasArray()){
			byte[] array = bbMsg.array();
			ByteArrayInputStream in = new ByteArrayInputStream(array);  
			ObjectInputStream sIn = new ObjectInputStream(in);  
			User user = (User) sIn.readObject();
			String json = JsonUtil.object2Json(user);
			ByteBuf copiedBuffer = Unpooled.copiedBuffer(json,CharsetUtil.UTF_8);
			ctx.write(copiedBuffer);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ByteBuf copiedBuffer = Unpooled.copiedBuffer("Hello world!",CharsetUtil.UTF_8);
//		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
		ctx.writeAndFlush(copiedBuffer).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();                //5
        ctx.close();             
	}

	
	
}
