package com.dingdangmaoup.fourd.bootstrap;


import com.dingdangmaoup.fourd.openai.client.OpenaiClient;
import com.dingdangmaoup.fourd.openai.entity.ChatResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dingdangmaoup.fourd", "com.alibaba.cola"})
public class BootstrapApplication {


  @Autowired
  OpenaiClient openaiClient;

  public static void main(String[] args) {
    SpringApplication.run(BootstrapApplication.class, args);
  }

  @PostConstruct
  public void test() {
    ChatResponse response = openaiClient.chat("你好");
    System.out.println(response);
  }

}
