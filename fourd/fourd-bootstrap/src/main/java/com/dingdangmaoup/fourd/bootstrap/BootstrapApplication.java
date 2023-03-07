package com.dingdangmaoup.fourd.bootstrap;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.dingdangmaoup.fourd", "com.alibaba.cola"})
public class BootstrapApplication {


  public static void main(String[] args) {
    SpringApplication.run(BootstrapApplication.class, args);
  }


}
