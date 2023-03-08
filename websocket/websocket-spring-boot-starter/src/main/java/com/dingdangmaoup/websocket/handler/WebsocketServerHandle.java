package com.dingdangmaoup.websocket.handler;


import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsocketServerHandle extends
    SimpleChannelInboundHandler<WebsocketMessagesProto.WebsocketRequest> {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    WebsocketMessagesProto.WebsocketResponse response = WebsocketMessagesProto.WebsocketResponse.newBuilder()
        .setResponseId(1).setResponseType(
            WebsocketMessagesProto.WebsocketResponse.ResponseType.OPENAI)
        .setResponseData(ByteString.copyFrom("i am ok".getBytes())).build();
    ctx.channel().writeAndFlush(response);
    log.info("server active...");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("server inactive...");
  }


  @Override
  protected void channelRead0(ChannelHandlerContext ctx, WebsocketMessagesProto.WebsocketRequest msg) throws Exception {
    log.info("server read...");
    log.info("request: {}", msg);
    WebsocketMessagesProto.WebsocketResponse response = WebsocketMessagesProto.WebsocketResponse.newBuilder()
        .setResponseId(1).setResponseType(
            WebsocketMessagesProto.WebsocketResponse.ResponseType.OPENAI)
        .setResponseData(ByteString.copyFrom("hello".getBytes())).build();
    ctx.channel().writeAndFlush(response);
  }
}
