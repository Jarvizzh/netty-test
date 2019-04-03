package com.jarvis.grpc.service.impl;

import com.jarvis.grpc.proto.MyReq;
import com.jarvis.grpc.proto.MyResp;
import com.jarvis.grpc.service.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author jarvis 2019/4/3
 */
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getRealName(MyReq request, StreamObserver<MyResp> responseObserver) {
        System.out.println("from client：" + request.getUsername());
        responseObserver.onNext(MyResp.newBuilder().setRealName("jarvis").build());
        responseObserver.onCompleted();
    }
}
