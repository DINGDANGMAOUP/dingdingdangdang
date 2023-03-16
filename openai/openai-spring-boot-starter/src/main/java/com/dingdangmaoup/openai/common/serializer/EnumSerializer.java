package com.dingdangmaoup.openai.common.serializer;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.dingdangmaoup.openai.type.ModelType;
import com.dingdangmaoup.openai.type.RoleType;
import java.lang.reflect.Type;

/**
 * enum序列化器
 *
 * @author dzhao1
 * @date 2023/03/06
 */
public class EnumSerializer implements ObjectWriter<Object> {


  @Override
  public void write(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType,
      long features) {
    if (object == null) {
      jsonWriter.writeNull();
      return;
    }
    if (object instanceof ModelType model) {
      write(jsonWriter, model);
    } else if (object instanceof RoleType role) {
      write(jsonWriter, role);
    } else {
      jsonWriter.writeString(object.toString());
    }
  }

  private void write(JSONWriter jsonWriter, ModelType type) {
    jsonWriter.writeString(type.getCode());
  }

  private void write(JSONWriter jsonWriter, RoleType type) {
    jsonWriter.writeString(type.getCode());
  }

}
