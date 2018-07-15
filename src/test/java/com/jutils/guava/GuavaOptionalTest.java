package com.jutils.guava;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.junit.Test;

public class GuavaOptionalTest {

	private Optional<String> parseString(String var) {
		if (var == null) {
			return Optional.fromNullable(var);
		} else {
			return Optional.of(var);
		}
	}

	@Test
	public void testOptional() {
		String var = null;
		Optional<String> optional = parseString(var);
		String or = optional.or("1");
		System.out.println(or);
		System.out.println(optional.isPresent());
		System.out.println(optional.or("2"));
	}

	private double sqrt(double value) {
		Preconditions.checkArgument(value >= 0, "input is negative: %s", value);
		return Math.sqrt(value);
	}

	@Test
	public void testPrecondition() {
		double sqrt = sqrt(-1);
	}
}
