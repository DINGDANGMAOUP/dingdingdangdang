package com.dingdangmaoup.websocket;


import com.dingdangmaoup.websocket.server.WebsocketServer;
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
      websocketServer.init();
      Thread.currentThread().join();
    };
  }
}
