package com.dingdangmaoup.openai.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

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

  //校验参数是否存在空
  public boolean checkNull() {
    return ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(object) || ObjectUtils.isEmpty(created)
        || ObjectUtils.isEmpty(choices) || ObjectUtils.isEmpty(usage);
  }
}