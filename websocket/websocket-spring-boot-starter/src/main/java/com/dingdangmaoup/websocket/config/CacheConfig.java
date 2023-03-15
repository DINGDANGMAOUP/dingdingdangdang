package com.dingdangmaoup.websocket.config;

import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Scheduler;
import io.netty.channel.Channel;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CacheConfig {

  @Bean
  public Cache<String, Channel> cacheManager() {
    return Caffeine.newBuilder().maximumSize(1000).recordStats()
        .scheduler(Scheduler.systemScheduler())
        .removalListener(
            (key, value, cause) -> log.info("淘汰通知,key:{},value:{},原因:{}", key, value, cause))
        .build();
  }

  /**
   * Caffeine消息队列
   */
  @Bean
  public Queue<WebsocketMessagesProto.WebsocketMessage> messageQueue() {
    return new LinkedBlockingQueue<>();
  }


}
