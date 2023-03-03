package com.dingdangmaoup.fourd.openai.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "openai")
public class OpenAIProperties {

  /**
   * OpenAI API Key
   */

  Config config;


  /**
   * proxy config
   */

  Proxy proxy;

  /**
   * openai配置
   */
  @Getter
  @Setter
  public static class Config {

    String apiKey;
  }

  /**
   * 代理配置
   */
  @Getter
  @Setter
  public static class Proxy {

    /**
     * enable proxy
     */
    boolean enableProxy = false;
    /**
     * proxy host
     */
    String host;
    /**
     * proxy port
     */
    Integer port;
  }
}
