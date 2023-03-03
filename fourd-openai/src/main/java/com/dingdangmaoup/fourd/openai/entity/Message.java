package com.dingdangmaoup.fourd.openai.entity;

import com.dingdangmaoup.fourd.openai.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  RoleType role;
  String content;
}
