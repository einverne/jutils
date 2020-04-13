package com.jutils.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since 2020-04-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String province;
	private String city;
	private String street;
	private int code;

}
