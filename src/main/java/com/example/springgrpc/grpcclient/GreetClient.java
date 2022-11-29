/*
 *  Copyright (c) 2022 GoTo
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO GOTO
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.springgrpc.grpcclient;

import com.example.greetservice.GreetRequest;
import com.example.greetservice.GreetResponse;
import com.example.greetservice.GreetServiceGrpc;
import com.example.greetservice.GreetServiceGrpc.GreetServiceBlockingStub;
import com.example.greetservice.HelloRequest;
import com.example.greetservice.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 * @author Piyush Kumar.
 * @since 29/11/22.
 */
public class GreetClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

        GreetServiceBlockingStub greetServiceBlockingStub = GreetServiceGrpc.newBlockingStub(channel);

        System.out.println("Calling Greet method ::: ");
        GreetRequest greetRequest = GreetRequest.newBuilder().setFirstName("Piyush").setLastName("Kumar").build();

        try {
            GreetResponse greetResponse = greetServiceBlockingStub.greet(greetRequest);
            System.out.println("Greet response is ::: " + greetResponse);
            System.out.println("Calling Greet method successful ::: ");

        }catch (StatusRuntimeException e){
            System.out.println("Exception occur while calling greet method ::: status : " + e.getStatus() + " message : " + e.getMessage());
        }

        System.out.println("Calling Hello method ::: ");
        HelloRequest helloRequest = HelloRequest.newBuilder().setFirstName("Piyush").setLastName("Kumar").build();

        try {
            HelloResponse helloResponse = greetServiceBlockingStub.hello(helloRequest);
            System.out.println("Hello response is ::: " + helloResponse);
            System.out.println("Calling Hello method successful ::: ");

        }catch (StatusRuntimeException e){
            System.out.println("Exception occur while calling hello method ::: status : " + e.getStatus() + " message : " + e.getMessage());
        }
    }
}
