package com.dingdangmaoup.openai.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(OpenaiProperties.class)
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
