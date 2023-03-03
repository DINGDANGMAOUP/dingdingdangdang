package com.dingdangmaoup.fourd.openai.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelType {
  //gpt-3.5-turbo
  GPT_3_5_TURBO("gpt-3.5-turbo"),
  //gpt-3.5-turbo-0301
  GPT_3_5_TURBO_0301("gpt-3.5-turbo-0301");


  final String code;

  public static ModelType fromCode(String code) {
    for (ModelType modelType : ModelType.values()) {
      if (modelType.getCode().equals(code)) {
        return modelType;
      }
    }
    return null;
  }


}
