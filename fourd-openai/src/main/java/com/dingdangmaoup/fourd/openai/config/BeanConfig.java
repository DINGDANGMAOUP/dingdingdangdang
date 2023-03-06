package com.dingdangmaoup.fourd.openai.config;

import com.dingdangmaoup.fourd.openai.common.constant.OpenaiConstants;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

@Import(OpenaiProperties.class)
@Configuration
@RequiredArgsConstructor
public class BeanConfig {

  private final OpenaiProperties openAIProperties;
  private Proxy proxy;


  @ConditionalOnProperty(name = "openai.proxy.enableProxy", havingValue = "true")
  @Bean(name = "proxy")
  @Order(1)
  public Proxy proxy() {
    proxy = new Proxy(Type.HTTP, new InetSocketAddress(openAIProperties.getProxy().getHost(),
        openAIProperties.getProxy().getPort()));
    return proxy;
  }

  @Bean(name = "okHttpClient")
  @Primary
  @Order(2)
  public OkHttpClient okHttpClient() {
    Builder builder = new OkHttpClient().newBuilder().proxy(proxy)
        .connectTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    return builder.build();
  }


}
