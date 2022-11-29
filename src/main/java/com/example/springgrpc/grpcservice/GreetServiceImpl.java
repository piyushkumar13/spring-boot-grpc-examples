/*
 *  Copyright (c) 2022 GoTo
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO GOTO
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.springgrpc.grpcservice;

import static com.example.greetservice.GreetServiceGrpc.GreetServiceImplBase;

import com.example.greetservice.GreetRequest;
import com.example.greetservice.GreetResponse;
import com.example.greetservice.HelloRequest;
import com.example.greetservice.HelloResponse;
import org.lognet.springboot.grpc.GRpcService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

/**
 * @author Piyush Kumar.
 * @since 29/11/22.
 */
@GRpcService
public class GreetServiceImpl extends GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {

        String name = request.getFirstName() + request.getLastName();

        String message  = "Welcome to the world of grpc ::: " + name;
        System.out.println(message);

        if (name.equalsIgnoreCase("KumarPiyush")){
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Name is not correct").asRuntimeException());
            return;
//            responseObserver.onCompleted(); // Either use return statement or oncompleted here so that flow will not go further in case of error.
        }

        System.out.println("executing");

        GreetResponse greetResponse = GreetResponse.newBuilder().setMessage(message).build();

        responseObserver.onNext(greetResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String name = request.getFirstName() + request.getLastName();

        String message  = "Hello to the world of grpc ::: " + name;
        System.out.println(message);

        if (name.equalsIgnoreCase("KumarPiyush")){
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Name is not correct").asRuntimeException());
            return;
//            responseObserver.onCompleted(); // Either use return statement or oncompleted here so that flow will not go further in case of error.
        }

        System.out.println("executing");

        HelloResponse helloResponse = HelloResponse.newBuilder().setMessage(message).build();

        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
