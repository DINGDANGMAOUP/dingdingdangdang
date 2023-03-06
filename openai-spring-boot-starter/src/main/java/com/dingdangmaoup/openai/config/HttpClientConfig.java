package com.dingdangmaoup.openai.config;

import com.dingdangmaoup.openai.common.constant.OpenaiConstants;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

  private Proxy proxy;

  /**
   * 通过set注入，解决构造器注入时，proxy不能为null的问题
   *
   * @param proxy proxy
   */
  @Autowired(required = false)
  public void setProxy(Proxy proxy) {
    this.proxy = proxy;
  }

  @Bean(name = "okHttpClient")
  public OkHttpClient okHttpClient() {
    Builder builder = new OkHttpClient().newBuilder().proxy(proxy)
        .connectTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(OpenaiConstants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    return builder.build();
  }


}
