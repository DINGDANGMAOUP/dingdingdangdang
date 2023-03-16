package com.dingdangmaoup.fourd.bootstrap;


import com.dingdangmaoup.websocket.server.WebsocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.dingdangmaoup.fourd", "com.alibaba.cola"})
public class BootstrapApplication {

@Autowired
  WebsocketServer websocketServer;
  public static void main(String[] args) {
    SpringApplication.run(BootstrapApplication.class, args);
  }


}
