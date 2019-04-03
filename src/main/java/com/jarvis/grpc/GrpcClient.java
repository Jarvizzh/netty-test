package com.jarvis.grpc;

import com.jarvis.grpc.proto.MyReq;
import com.jarvis.grpc.proto.MyResp;
import com.jarvis.grpc.service.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author jarvis 2019/4/3
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8888)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub blockingStub = UserServiceGrpc.newBlockingStub(managedChannel);

        MyReq req = MyReq.newBuilder().setUsername("hello").build();
        MyResp resp = blockingStub.getRealName(req);
        System.out.println(resp.getRealName());
    }


}
