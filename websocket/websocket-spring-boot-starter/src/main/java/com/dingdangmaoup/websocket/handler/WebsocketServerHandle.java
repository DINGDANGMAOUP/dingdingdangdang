package com.dingdangmaoup.websocket.handler;


import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketMessage;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Sharable
public class WebsocketServerHandle extends
    SimpleChannelInboundHandler<WebsocketMessagesProto.WebsocketMessage> {

  private int times = 0;
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    log.info("客户端与服务端会话连接成功");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    log.info("客户端与服务端会话连接断开");
  }


  @Override
  protected void channelRead0(ChannelHandlerContext ctx,
      WebsocketMessagesProto.WebsocketMessage msg) throws Exception {

    log.info("server read...");
    log.info("server read request: {}", msg);
    WebsocketMessage build = WebsocketMessage.newBuilder().build();
    ctx.writeAndFlush(build);
  }

  /**
   * 服务端监听到客户端异常
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.info("服务端监听到客户端异常");
  }


}