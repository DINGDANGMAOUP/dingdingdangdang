package com.dingdangmaoup.openai.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dingdangmaoup.openai.common.serializer.EnumSerializer;
import com.dingdangmaoup.openai.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.serializer.Deserializer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	@JSONField(serializeUsing = EnumSerializer.class, deserializeUsing = Deserializer.class)
	private RoleType role;
	private String content;
}