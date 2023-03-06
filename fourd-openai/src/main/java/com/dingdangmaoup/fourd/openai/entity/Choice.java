package com.dingdangmaoup.fourd.openai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
	private String finishReason;
	private Integer index;
	private Message message;
}