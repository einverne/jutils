package com.jutils.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author einverne
 * @date 2019-03-27
 */
@Data
public class EmployeeFastjson {
	@JSONField(name = "familyName")
	private String name;
	private int age;
	@JSONField(name = "salary")
	private float wage;
}
