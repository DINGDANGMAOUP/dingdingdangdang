package com.dingdangmaoup.websocket.server;

import com.dingdangmaoup.websocket.config.NettyProperties;
import com.dingdangmaoup.websocket.handler.WebsocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(NettyProperties.class)
public class WebsocketServer {

 private final NettyProperties nettyProperties;

  @Autowired
  public WebsocketServer(NettyProperties nettyProperties) {
    this.nettyProperties = nettyProperties;
  }

  public void init() throws InterruptedException {
    EventLoopGroup bossGroup = new NioEventLoopGroup(nettyProperties.getBossGroupThreadCount());
    EventLoopGroup workerGroup = new NioEventLoopGroup(nettyProperties.getWorkerGroupThreadCount());

    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new WebsocketServerInitializer(nettyProperties));
      Channel ch = b.bind(nettyProperties.getHost(), nettyProperties.getPort()).sync().channel();
      ch.closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
