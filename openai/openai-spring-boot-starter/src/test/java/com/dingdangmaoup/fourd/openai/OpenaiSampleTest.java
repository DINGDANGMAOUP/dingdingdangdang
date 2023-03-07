package com.dingdangmaoup.fourd.openai;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.dingdangmaoup.openai.entity.ChatResponse;
import org.junit.jupiter.api.Test;

public class OpenaiSampleTest {

  @Test
  void test() {
      String jsonStr="{\n"
      + "    \"id\": \"chatcmpl-6qzy8WTaMBrBLFMJwgOMcO82OtrUY\",\n"
      + "    \"object\": \"chat.completion\",\n"
      + "    \"created\": 1678089284,\n"
      + "    \"model\": \"gpt-3.5-turbo-0301\",\n"
      + "    \"usage\": {\n"
      + "        \"prompt_tokens\": 9,\n"
      + "        \"completion_tokens\": 18,\n"
      + "        \"total_tokens\": 27\n"
      + "    },\n"
      + "    \"choices\": [\n"
      + "        {\n"
      + "            \"message\": {\n"
      + "                \"role\": \"assistant\",\n"
      + "                \"content\": \"\\n\\n你好，有什么需要帮助的吗？\"\n"
      + "            },\n"
      + "            \"finish_reason\": \"stop\",\n"
      + "            \"index\": 0\n"
      + "        }\n"
      + "    ]\n"
      + "}";
    ChatResponse chatResponse = JSONObject.parseObject(jsonStr, ChatResponse.class);
    System.out.println(chatResponse);
  }

}
