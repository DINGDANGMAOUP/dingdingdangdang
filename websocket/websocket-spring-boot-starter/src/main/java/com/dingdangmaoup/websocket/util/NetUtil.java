package com.dingdangmaoup.websocket.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import lombok.extern.slf4j.Slf4j;

/**
 * NetUtil
 *
 * @author dzhao1
 * @date 2023/03/16
 */
@Slf4j
public class NetUtil {

  private NetUtil() {
  }

  /**
   * 获取本地主机
   *
   * @return {@link String}
   */
  public static String getLocalhost() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (Exception e) {
      log.error("Failed to obtain the local ip address. Procedure", e);
    }
    return null;
  }

  /**
   * 随机端口
   *
   * @return int
   */
  public static int randomPort() {
    int port = 0;
    try {
      ServerSocket socket = new ServerSocket(0);
      port = socket.getLocalPort();
      socket.close();
    } catch (IOException e) {
      log.error("Could not find an available port.",e);
    }
    return port;
  }
}
