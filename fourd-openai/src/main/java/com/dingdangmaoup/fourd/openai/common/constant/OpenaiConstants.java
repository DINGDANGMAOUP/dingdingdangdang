package com.dingdangmaoup.fourd.openai.common.constant;

import okhttp3.MediaType;

/**
 * openai常量
 *
 * @author dzhao1
 * @date 2023/03/06
 */
public interface OpenaiConstants {

  String TOKEN_HEADER = "Authorization";
  String TOKEN_PREFIX = "Bearer ";
  String CONFIG_PREFIX = "openai.config.api-key";
  String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
  MediaType JSON
      = MediaType.get("application/json; charset=utf-8");
  /**
   * 默认超时时间 10分钟 MILLISECONDS
   */
  Long DEFAULT_TIMEOUT = 10 * 60 * 1000L;


}
