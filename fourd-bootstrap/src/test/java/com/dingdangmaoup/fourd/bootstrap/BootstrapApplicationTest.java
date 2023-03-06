package com.dingdangmaoup.fourd.bootstrap;

import static org.junit.jupiter.api.Assertions.*;

import com.dingdangmaoup.fourd.openai.client.OpenaiClient;
import com.dingdangmaoup.fourd.openai.entity.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootstrapApplicationTest {
  @Autowired
  OpenaiClient openaiClient;

  @Test
  void contextLoads() {
    ChatResponse response = openaiClient.chat("你好");
    System.out.println(response);
  }
}