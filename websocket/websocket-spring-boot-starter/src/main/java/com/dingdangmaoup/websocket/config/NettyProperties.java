package com.dingdangmaoup.websocket.config;

import com.dingdangmaoup.websocket.util.NetUtil;
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
  private int port = NetUtil.randomPort();
  /**
   * websocket server host(多网卡时，需手动指定网卡) <p/>default is localhost
   */
  private String host = NetUtil.getLocalhost();

  /**
   * bossGroupThreadCount
   */
  private int bossGroupThreadCount = 1;
  /**
   * workerGroupThreadCount
   */
  private int workerGroupThreadCount = 200;

  /**
    * backlog
   */
  private int backlog = 1024;
  /**
   * maxFramePayloadLength
   */
  private int maxFramePayloadLength = 65536;

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
