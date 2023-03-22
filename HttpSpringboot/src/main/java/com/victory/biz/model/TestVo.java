package com.victory.biz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestVo {

	@JsonProperty("test1")
	private String test1;

	@JsonProperty("test2")
	private String test2;

	@JsonProperty("test3")
	private String test3;

}
