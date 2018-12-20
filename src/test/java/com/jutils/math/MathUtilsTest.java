package com.jutils.math;


import org.junit.Assert;
import org.junit.Test;

public class MathUtilsTest {

	@Test
	public void isOdd() {
		Assert.assertTrue(MathUtils.isOdd(1));
		Assert.assertFalse(MathUtils.isOdd(2));
		Assert.assertTrue(MathUtils.isOdd(-3));
	}
}