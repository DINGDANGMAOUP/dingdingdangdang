package com.dingdangmaoup.fourd.bootstrap;


import com.dingdangmaoup.openai.config.OpenaiProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootstrapApplicationTest {

  @Autowired
  OpenaiProperties openaiProperties;

  @Test
  void contextLoads() {

  }
}