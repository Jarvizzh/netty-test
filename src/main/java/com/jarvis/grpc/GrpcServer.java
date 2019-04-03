package com.jarvis.grpc;

import com.jarvis.grpc.service.impl.UserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author jarvis 2019/4/3
 */
public class GrpcServer {

    private Server server;

    private void start() throws IOException {
        this.server = ServerBuilder.forPort(8888)
                .addService(new UserServiceImpl())
                .build()
                .start();
        System.out.println("server started!");

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    System.out.println("jvm shutdown");
                    server.shutdown();
                })
        );
    }

    private void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) {
        GrpcServer server = new GrpcServer();
        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
        }
    }


}
