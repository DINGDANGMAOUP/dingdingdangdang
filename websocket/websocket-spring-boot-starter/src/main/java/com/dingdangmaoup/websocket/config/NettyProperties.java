package com.dingdangmaoup.websocket.config;

import com.dingdangmaoup.websocket.util.IPUtil;
import java.util.concurrent.TimeUnit;
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
  private int port = 19999;
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

  /**
   * heartbeat
   */
  private HeartBeat heartBeat=new HeartBeat();

  @Getter
  @Setter
  public static class HeartBeat {

    /**
     * readerIdleTime
     */
    private int readerIdleTime = 60;
    /**
     * writerIdleTime
     */
    private int writerIdleTime = 0;
    /**
     * allIdleTime
     */
    private int allIdleTime = 0;

    /**
     * unit
     */
    private TimeUnit unit = TimeUnit.SECONDS;
  }
}
