package com.dingdangmaoup.websocket;

import com.dingdangmaoup.websocket.server.WebsocketServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebsocketApplicationTests {
@Autowired
  WebsocketServer websocketServer;
  @Test
  void contextLoads() throws InterruptedException {
    websocketServer.init();
  }

}
