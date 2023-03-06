package com.dingdangmaoup.openai.entity;

import com.alibaba.fastjson2.annotation.JSONAutowired;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usage {

  @JSONField(name = "completion_tokens")
  private Integer completionTokens;
  @JSONField(name = "prompt_tokens")
  private Integer promptTokens;
  @JSONField(name = "total_tokens")
  private Integer totalTokens;
}