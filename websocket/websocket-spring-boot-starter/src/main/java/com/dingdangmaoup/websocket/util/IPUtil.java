package com.dingdangmaoup.websocket.util;

import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPUtil {

  private IPUtil() {
  }

  public static String getLocalhost() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (Exception e) {
      log.error("获取本机ip失败", e);
    }
    return null;
  }

}
