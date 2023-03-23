package com.dingdangmaoup.openai.client;

import com.alibaba.fastjson2.JSONObject;
import com.dingdangmaoup.openai.common.condition.OpenaiCondition;
import com.dingdangmaoup.openai.common.constant.OpenaiConstants;
import com.dingdangmaoup.openai.config.OpenaiProperties;
import com.dingdangmaoup.openai.entity.ChatRequest;
import com.dingdangmaoup.openai.entity.ChatResponse;
import com.dingdangmaoup.openai.entity.Message;
import com.dingdangmaoup.openai.entity.R;
import com.dingdangmaoup.openai.entity.R.RBuilder;
import com.dingdangmaoup.openai.exception.OpenaiException;
import com.dingdangmaoup.openai.type.ModelType;
import com.dingdangmaoup.openai.type.RoleType;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties(OpenaiProperties.class)
@Conditional(OpenaiCondition.class)
@Import(OkHttpClient.class)
@RequiredArgsConstructor
public class OpenaiClient {

  private final OpenaiProperties openaiProperties;

  private final OkHttpClient okHttpClient;

  /**
   * 使用默认模型 对话
   *
   * @param content 内容
   * @return {@link ChatResponse}
   */
  public R chat(String content) {
    Message message = Message.builder().role(RoleType.USER).content(content).build();
    ChatRequest request = ChatRequest.builder().model(ModelType.DEFAULT)
        .messages(Collections.singletonList(message)).build();
    return chat(request);
  }

  /**
   * 闲谈，聊天
   *
   * @param chatRequest 聊天请求
   * @return {@link ChatResponse}
   */
  public R chat(ChatRequest chatRequest) {
    RBuilder builder = R.builder();
    builder.success(true);
    ChatResponse chatResponse = null;
    String bodyStr = null;
    RequestBody body = RequestBody.create(JSONObject.toJSONString(chatRequest),
        OpenaiConstants.JSON);
    Request request = new Request.Builder().url(OpenaiConstants.OPENAI_URL)
        .addHeader(OpenaiConstants.TOKEN_HEADER,
            OpenaiConstants.TOKEN_PREFIX + openaiProperties.getConfig().getApiKey())
        .post(body)
        .build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      bodyStr = response.body() != null ? response.body().string() : null;
      chatResponse = JSONObject.parseObject(bodyStr, ChatResponse.class);
    } catch (IOException e) {
      throw new OpenaiException(e.getMessage());
    }
    if (chatResponse != null && !chatResponse.checkNull()) {
      builder.success(true);
      builder.data(chatResponse);
    } else {
      builder.success(false);
      builder.message(bodyStr);
    }
    return builder.build();
  }

}
