package ru.tinkoff.lections.sender.resource.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GrpcMessageService extends SenderServiceGrpc.SenderServiceImplBase {

    @Override
    public void getMessage(GetMessageRequest request, StreamObserver<GetMessageResponse> responseObserver) {
        GetMessageResponse response = GetMessageResponse.newBuilder()
                .setMessage(Message
                        .newBuilder()
                        .setId(request.getId())
                        .setAuthor("author")
                        .setContent("Greeting message!")
                        .setTimestamp(Timestamp.newBuilder().build())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
