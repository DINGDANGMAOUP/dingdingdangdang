package com.dingdangmaoup.fourd.openai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usage {

  private Integer completionTokens;
  private Integer promptTokens;
  private Integer totalTokens;
}