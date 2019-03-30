package com.jarvis.netty.protobuf_6;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Random;

/**
 * @author jarvis 2019/3/30
 */
public class Client {

    public static void main(String[] args) {
        EventLoopGroup workerG = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(workerG)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new MyInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", Server.port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerG.shutdownGracefully();
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
            pipeline.addLast(new MyClientHandler());
        }
    }

    static class MyClientHandler extends SimpleChannelInboundHandler<PbInfo.MyMessage> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, PbInfo.MyMessage msg) throws Exception {

        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            PbInfo.Person person = PbInfo.Person.newBuilder()
                    .setName("jarvis")
                    .setAge(20)
                    .setId("hello2world")
                    .addArr("first str")
                    .addArr("second str")
                    .build();

            PbInfo.Cat cat = PbInfo.Cat.newBuilder()
                    .setName("cat")
                    .setAge(1)
                    .build();

            PbInfo.Dog dog = PbInfo.Dog.newBuilder()
                    .setName("dog")
                    .setAge(1)
                    .build();

            int i = new Random().nextInt(3);
            PbInfo.MyMessage data;
            if (i == 1) {
                data = PbInfo.MyMessage.newBuilder()
                        .setDataType(PbInfo.MyMessage.DataType.PersonType)
                        .setPerson(person)
                        .build();
            } else if (i == 2) {
                data = PbInfo.MyMessage.newBuilder()
                        .setDataType(PbInfo.MyMessage.DataType.CatType)
                        .setCat(cat)
                        .build();
            } else {
                data = PbInfo.MyMessage.newBuilder()
                        .setDataType(PbInfo.MyMessage.DataType.DogType)
                        .setDog(dog)
                        .build();
            }
            ctx.writeAndFlush(data);
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            cause.printStackTrace();
            ctx.close();
        }
    }


}
