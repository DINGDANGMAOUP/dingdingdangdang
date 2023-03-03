package com.dingdangmaoup.fourd.openai.client;

import com.dingdangmaoup.fourd.openai.condition.OpenaiCondition;
import com.dingdangmaoup.fourd.openai.config.OpenAIProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;

@EnableConfigurationProperties(OpenAIProperties.class)
@Conditional(OpenaiCondition.class)
public class OpenAIClient {

  @Autowired
  OpenAIProperties openAIProperties;


}
