package com.dingdangmaoup.websocket.server;

import com.dingdangmaoup.websocket.config.NettyProperties;
import com.dingdangmaoup.websocket.handler.WebsocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(NettyProperties.class)
@RequiredArgsConstructor
@Slf4j
public class WebsocketServer {

  private final NettyProperties nettyProperties;
  private final WebsocketServerInitializer websocketServerInitializer;


  public void init() {
    EventLoopGroup bossGroup = new NioEventLoopGroup(nettyProperties.getBossGroupThreadCount());
    EventLoopGroup workerGroup = new NioEventLoopGroup(nettyProperties.getWorkerGroupThreadCount());
    ServerBootstrap b = new ServerBootstrap();
    b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.INFO))
        .option(ChannelOption.SO_BACKLOG, nettyProperties.getBacklog())
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childHandler(websocketServerInitializer);
    try {
      b.bind(nettyProperties.getPort()).channel().closeFuture().sync();
    } catch (InterruptedException e) {
      log.error("websocket server init error", e);
      Thread.currentThread().interrupt();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }


}
