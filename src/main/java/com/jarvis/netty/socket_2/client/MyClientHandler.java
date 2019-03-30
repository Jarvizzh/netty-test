package com.jarvis.netty.socket_2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author jarvis 2019/3/28
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("get msg: " + msg);
        ctx.writeAndFlush("form client@" + ctx.channel().id() + LocalDateTime.now());
    }

}
