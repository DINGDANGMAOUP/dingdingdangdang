package com.dingdangmaoup.fourd.bootstrap;


import com.dingdangmaoup.openai.client.OpenaiClient;
import com.dingdangmaoup.openai.entity.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootstrapApplicationTest {

  @Autowired
  OpenaiClient openaiClient;


  @Test
  void contextLoads() {
    ChatResponse response = openaiClient.chat("请用不少于100写自我介绍");
    System.out.println(response);
  }
}