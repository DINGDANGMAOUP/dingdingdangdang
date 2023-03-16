package com.dingdangmaoup.openai.common.deserializer;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.dingdangmaoup.openai.type.ModelType;
import com.dingdangmaoup.openai.type.RoleType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * enum 反序列化器
 *
 * @author dzhao1
 * @date 2023/03/06
 */
public class EnumDeserializer implements ObjectReader<Object> {

  @Override
  public Object readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
    String code = jsonReader.getString();
    Map<String, Enum<?>> enumMap = new HashMap<>();
    for (ModelType modelType : ModelType.values()) {
      enumMap.put(modelType.getCode(), modelType);
    }
    for (RoleType roleType : RoleType.values()) {
      enumMap.put(roleType.getCode(), roleType);
    }
    return enumMap.get(code);
  }

}
