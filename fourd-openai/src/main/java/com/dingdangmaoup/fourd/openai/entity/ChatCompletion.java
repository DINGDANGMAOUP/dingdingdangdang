package com.dingdangmaoup.fourd.openai.entity;

import com.dingdangmaoup.fourd.openai.type.ModelType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletion {

  ModelType model;
  List<Message> messages;
}
