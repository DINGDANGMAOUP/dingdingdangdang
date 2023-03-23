package com.dingdangmaoup.openai.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
	@JSONField(name="finish_reason")
	private String finishReason;
	@JSONField(name="index")
	private Integer index;
	private Message message;


}