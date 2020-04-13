package com.jutils.base;

import java.util.List;
import jodd.json.meta.JSON;
import lombok.Data;

/**
 * @since 2020-04-13
 */
@Data
public class Person {

	private String name;
	private String birthDay;
	private int idNum;
	private int age;
	@JSON(name = "address")
	private List<Address> addressList;
	private List<String> phoneNums;
}
