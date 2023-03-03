package com.dingdangmaoup.fourd.openai.config;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Import(OpenAIProperties.class)
@Configuration
public class BeanConfig {

  @Autowired
  OpenAIProperties openAIProperties;

  @Bean
  @ConditionalOnProperty(name = "openai.proxy.enableProxy", havingValue = "true")
  public OkHttpClient okHttpClientProxy() {
    Builder builder = new OkHttpClient().newBuilder();
    builder.proxy(new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress(
        openAIProperties.getProxy().getHost(), openAIProperties.getProxy().getPort())));
    return builder.build();
  }

  @Primary
  @Bean
  @ConditionalOnProperty(name = "openai.proxy.enableProxy", havingValue = "false", matchIfMissing = true)
  public OkHttpClient okHttpClient() {
    Builder builder = new OkHttpClient().newBuilder();
    return builder.build();
  }
}
