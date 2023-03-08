package com.dingdangmaoup.websocket.config;

import com.dingdangmaoup.websocket.util.IPUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "websocket")
public class NettyProperties {

  /**
   * websocket server path
   */
  private String path = "/websocket";
  /**
   * websocket server port
   */
  private int port = 6666;
  /**
   * websocket server host <p/>default is localhost
   */
  private String host = IPUtil.getLocalhost();

  /**
   * bossGroupThreadCount
   */
  private int bossGroupThreadCount = 1;
  /**
   * workerGroupThreadCount
   */
  private int workerGroupThreadCount = 200;

}
