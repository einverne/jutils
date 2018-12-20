package com.jutils.math;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

public class BigDecimalUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_scale() {
		BigDecimal bd = new BigDecimal("1.54365412");
		System.out.println("toString() " + bd.toString() + " toPlainString() " + bd.toPlainString());
		System.out.println("toEngineeringString() " + bd.toEngineeringString());
		bd = new BigDecimal(1.54365412);
		System.out.println("toString() " + bd.toString() + " toPlainString() " + bd.toPlainString());
		System.out.println("toEngineeringString() " + bd.toEngineeringString());
		bd = new BigDecimal("0.54365412");
		System.out.println("toString() " + bd.toString() + " toPlainString() " + bd.toPlainString());
		System.out.println("toEngineeringString() " + bd.toEngineeringString());
	}

	@Test
	public void test_rounding() {
		BigDecimal bd = new BigDecimal("1.544");
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("toString() " + bd.toString() + " toPlainString() " + bd.toPlainString());
		System.out.println("toEngineeringString() " + bd.toEngineeringString());
		bd = new BigDecimal("1.545");
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("toString() " + bd.toString() + " toPlainString() " + bd.toPlainString());
		System.out.println("toEngineeringString() " + bd.toEngineeringString());
	}

	@Test
	public void add() {
		String result = BigDecimalUtils.add("11.231", "1.3243212542213111");
		System.out.println(result);
	}

	@Test
	public void div() {
	}

	@Test
	public void compareTo() {
	}

	@Test
	public void returnMin() {
	}

	@Test
	public void returnMax() {
	}

	@Test
	public void getValue() {
	}

	@Test
	public void getBigDecimal() {
	}

	@Test
	public void getBigDecimal1() {
	}

	@Test
	public void bigDecimalToLong() {
	}

	@Test
	public void bigDecimalToInteger() {
	}
}