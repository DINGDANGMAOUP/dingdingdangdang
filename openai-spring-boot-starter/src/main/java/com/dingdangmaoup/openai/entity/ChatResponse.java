package com.dingdangmaoup.openai.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

  private String id;
  private String object;
  private Long created;
  private List<Choice> choices;
  private Usage usage;
}