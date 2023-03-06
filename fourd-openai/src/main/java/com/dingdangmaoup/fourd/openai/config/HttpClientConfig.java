package com.dingdangmaoup.fourd.openai.config;

import com.dingdangmaoup.fourd.openai.common.constant.OpenaiConstants;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import(ProxyConfig.class)
public class HttpClientConfig {


  private final Proxy proxy;

  @Autowired(required = false)
  public HttpClientConfig(Proxy proxy) {
    this.proxy = proxy;
  }

  @Bean(name = "okHttpClient")
  @Primary
  public OkHttpClient okHttpClient() {
    Builder builder = new OkHttpClient().newBuilder().proxy(proxy)
        .connectTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    return builder.build();
  }


}
