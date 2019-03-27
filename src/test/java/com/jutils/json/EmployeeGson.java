package com.jutils.json;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author einverne
 * @date 2019-03-27
 */
@Data
public class EmployeeGson {

	@SerializedName(value = "fullname", alternate = {"Name", "familyName"})
	private String name;
	private int age;
	@SerializedName("salary")
	private float wage;
}
