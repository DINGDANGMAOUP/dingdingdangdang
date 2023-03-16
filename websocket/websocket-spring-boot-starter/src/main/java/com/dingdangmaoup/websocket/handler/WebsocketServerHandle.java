package com.dingdangmaoup.websocket.handler;


import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketMessage;
import com.github.benmanes.caffeine.cache.Cache;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Queue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Sharable
@RequiredArgsConstructor
@Component
public class WebsocketServerHandle extends
    SimpleChannelInboundHandler<WebsocketMessagesProto.WebsocketMessage> {


  private final Cache<String, Channel> channelCache;

  private final Queue<WebsocketMessage> messageQueue;


  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    channelCache.put(ctx.channel().id().asLongText(), ctx.channel());
    log.info("客户端与服务端会话连接成功");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    channelCache.invalidate(ctx.channel().id().asLongText());
    log.info("客户端与服务端会话连接断开");
  }


  @Override
  protected void channelRead0(ChannelHandlerContext ctx,
      WebsocketMessagesProto.WebsocketMessage msg) throws Exception {
    log.info("server read : {}", msg);
    messageQueue.offer(msg);
  }

  /**
   * 服务端监听到客户端异常
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.info("服务端监听到客户端({})异常:{}", ctx, cause.getMessage());
  }


}