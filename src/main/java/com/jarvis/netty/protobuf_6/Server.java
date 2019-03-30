package com.jarvis.netty.protobuf_6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author jarvis 2019/3/30
 */
public class Server {

    static final int port = 8088;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MyInitializer());
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class MyInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new ProtobufDecoder(PbInfo.MyMessage.getDefaultInstance()));
            pipeline.addLast(new ProtobufEncoder());
            pipeline.addLast(new ProtobufVarint32FrameDecoder());
            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
            pipeline.addLast(new MyHandler());
        }
    }

    static class MyHandler extends SimpleChannelInboundHandler<PbInfo.MyMessage> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, PbInfo.MyMessage msg) throws Exception {
            PbInfo.MyMessage.DataType type = msg.getDataType();
            switch (type) {
                case PersonType:
                    PbInfo.Person person = msg.getPerson();
                    System.out.println(person);
                    break;
                case CatType:
                    PbInfo.Cat cat = msg.getCat();
                    System.out.println(cat);
                    break;
                case DogType:
                    PbInfo.Dog dog = msg.getDog();
                    System.out.println(dog);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            cause.printStackTrace();
            ctx.close();
        }
    }

}
