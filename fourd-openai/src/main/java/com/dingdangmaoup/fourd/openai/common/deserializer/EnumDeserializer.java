package com.dingdangmaoup.fourd.openai.common.deserializer;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.dingdangmaoup.fourd.openai.type.ModelType;
import com.dingdangmaoup.fourd.openai.type.RoleType;
import java.lang.reflect.Type;

/**
 * enum 反序列化器
 *
 * @author dzhao1
 * @date 2023/03/06
 */
public class EnumDeserializer implements ObjectReader {

  @Override
  public Object readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
    String code = jsonReader.getString();
    ModelType[] modelTypes = ModelType.values();
    RoleType[] roleTypes = RoleType.values();
    for (ModelType modelType : modelTypes) {
      if (modelType.getCode().equals(code)) {
        return modelType;
      }
    }
    for (RoleType roleType : roleTypes) {
      if (roleType.getCode().equals(code)) {
        return roleType;
      }
    }
    return null;
  }

}
