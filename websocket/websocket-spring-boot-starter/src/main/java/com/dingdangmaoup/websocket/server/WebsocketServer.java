package com.dingdangmaoup.websocket.server;

import com.dingdangmaoup.websocket.config.NettyProperties;
import com.dingdangmaoup.websocket.handler.WebsocketServerInitializer;
import com.github.benmanes.caffeine.cache.Cache;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties(NettyProperties.class)
@Import(Cache.class)
@RequiredArgsConstructor
public class WebsocketServer {

  private final NettyProperties nettyProperties;
  private final Cache<String, Channel> cache;
  private final WebsocketServerInitializer websocketServerInitializer;


  public void init() throws InterruptedException {
    EventLoopGroup bossGroup = new NioEventLoopGroup(nettyProperties.getBossGroupThreadCount());
    EventLoopGroup workerGroup = new NioEventLoopGroup(nettyProperties.getWorkerGroupThreadCount());
    ServerBootstrap b = new ServerBootstrap();
    b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.INFO))
        .option(ChannelOption.SO_BACKLOG, nettyProperties.getBacklog())
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childHandler(websocketServerInitializer);
    b.bind(nettyProperties.getPort()).channel().closeFuture().sync();
  }

  /**
   * 广播
   */
  public void broadcast(String msg) {
    cache.asMap().values().forEach(channel -> channel.writeAndFlush(msg));
  }
}
