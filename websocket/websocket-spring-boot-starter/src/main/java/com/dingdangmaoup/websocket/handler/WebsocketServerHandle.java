package com.dingdangmaoup.websocket.handler;


import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketResponse;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketResponse.ResponseType;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsocketServerHandle extends
    SimpleChannelInboundHandler<WebSocketFrame> {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    WebsocketResponse response = WebsocketResponse.newBuilder().setResponseId(1).setResponseType(
        ResponseType.OPENAI).setResponseData(ByteString.copyFrom("hello".getBytes())).build();
    ctx.channel().writeAndFlush(response);
    log.info("server active...");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("server inactive...");
  }


  @Override
  protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
    log.info("server read...");
//    WebsocketRequest request = WebsocketRequest.parseFrom(msg.content().array());
    log.info("request: {}", ((TextWebSocketFrame)msg).text());
    WebsocketResponse response = WebsocketResponse.newBuilder().setResponseId(1).setResponseType(
        ResponseType.OPENAI).setResponseData(ByteString.copyFrom("hello".getBytes())).build();
    ctx.channel().writeAndFlush(response);
  }
}
