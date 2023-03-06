package com.dingdangmaoup.fourd.openai.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dingdangmaoup.fourd.openai.common.serializer.EnumSerializer;
import com.dingdangmaoup.fourd.openai.type.ModelType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.serializer.Deserializer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {

  @JSONField(serializeUsing = EnumSerializer.class, deserializeUsing = Deserializer.class)
  ModelType model;
  List<Message> messages;
}
