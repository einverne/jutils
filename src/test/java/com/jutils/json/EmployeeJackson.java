package com.jutils.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author einverne
 * @date 2019-03-27
 */
@Data
public class EmployeeJackson {

	@JsonProperty("familyName")
	private String name;
	private int age;
	@JsonProperty("salary")
	private float wage;

}
