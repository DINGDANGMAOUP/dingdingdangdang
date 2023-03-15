package com.dingdangmaoup.websocket.handler;

import com.dingdangmaoup.websocket.config.NettyProperties;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Sharable
public class IdServerHandler extends IdleStateHandler {


  public IdServerHandler(NettyProperties nettyProperties) {
    super(nettyProperties.getHeartBeat().getReaderIdleTime(),
        nettyProperties.getHeartBeat().getWriterIdleTime(),
        nettyProperties.getHeartBeat().getAllIdleTime(), nettyProperties.getHeartBeat().getUnit());
  }

//  @Override
//  protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
//    ctx.channel().close();
//  }
}
