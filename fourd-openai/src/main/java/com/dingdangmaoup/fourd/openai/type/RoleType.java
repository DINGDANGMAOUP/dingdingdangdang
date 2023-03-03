package com.dingdangmaoup.fourd.openai.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
  // “system”, “user”, or “assistant”
  SYSTEM("system"),
  USER("user"),
  ASSISTANT("assistant");


  final String code;

  public static RoleType fromCode(String code) {
    for (RoleType roleType : RoleType.values()) {
      if (roleType.getCode().equals(code)) {
        return roleType;
      }
    }
    return null;
  }
}
