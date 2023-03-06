package com.dingdangmaoup.fourd.openai.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(OpenaiProperties.class)
@Configuration
public class ProxyConfig {

  private final OpenaiProperties openaiProperties;

  @Autowired
  public ProxyConfig(OpenaiProperties openaiProperties) {
    this.openaiProperties = openaiProperties;
  }

  @ConditionalOnProperty(name = "openai.proxy.enable-proxy", havingValue = "true")
  @Bean(name = "proxy")
  public Proxy proxy() {
    return new Proxy(Type.HTTP, new InetSocketAddress(openaiProperties.getProxy().getHost(),
        openaiProperties.getProxy().getPort()));
  }
}
