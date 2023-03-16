package com.dingdangmaoup.websocket;


import com.dingdangmaoup.websocket.server.WebsocketServer;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebsocketTestApplication {

  @Autowired
  private WebsocketServer websocketServer;

  public static void main(String[] args) {
    SpringApplication.run(WebsocketTestApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner() {
    return args -> {
      new Thread(() -> {
        try {
          websocketServer.init();
          Thread.currentThread().join();
        } catch (InterruptedException e) {
        }
      }).start();
    };
  }
}
