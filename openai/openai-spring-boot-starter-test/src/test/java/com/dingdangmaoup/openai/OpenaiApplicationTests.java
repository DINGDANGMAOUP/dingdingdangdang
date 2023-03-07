package com.dingdangmaoup.openai;

import com.dingdangmaoup.openai.client.OpenaiClient;
import com.dingdangmaoup.openai.entity.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenaiApplicationTests {
@Autowired
  OpenaiClient openaiClient;
  @Test
  void contextLoads() {
//    ChatResponse response = openaiClient.chat("早上好");
//    System.out.println(response);
  }
}
