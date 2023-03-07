package com.dingdangmaoup.openai.common.condition;

import com.dingdangmaoup.openai.common.constant.OpenaiConstants;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OpenaiCondition implements Condition {

  /**
   * 匹配
   *
   * @param context：可以获得Bean工厂、Environment、bean定义注册器、ClassLoader、ResourceLoader
   * @param metadata：可以获得注解信息，比如：方法名称、
   * @return boolean
   */
  @Override
  public boolean matches(ConditionContext context, @NotNull AnnotatedTypeMetadata metadata) {
    Environment environment = context.getEnvironment();
    String property = environment.getProperty(OpenaiConstants.CONFIG_PREFIX);
    return property != null && !property.isEmpty();
  }

}