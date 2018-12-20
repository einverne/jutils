package com.jutils.base;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class MoneyUtilsTest {
	private BigDecimal smallMoney = new BigDecimal("0.1234");
	private BigDecimal bigMoney = new BigDecimal("213453");
	private BigDecimal complexMoney = new BigDecimal("223453.4321");

	@Test
	public void number2CNMontray() {
		String s = MoneyUtils.number2CNMontray("1.2");
		Assert.assertEquals("壹元贰角", s);
		s = MoneyUtils.number2CNMontray("1234.56");
		Assert.assertEquals("壹仟贰佰叁拾肆元伍角陆分", s);

		s = MoneyUtils.number2CNMontray(new BigDecimal("1.2"));
		Assert.assertEquals("壹元贰角", s);
		s = MoneyUtils.number2CNMontray(new BigDecimal("1234.56"));
		Assert.assertEquals("壹仟贰佰叁拾肆元伍角陆分", s);
	}

	@Test
	public void accountantMoney() {
		String m = MoneyUtils.accountantMoney(new BigDecimal("1854.23"));
		Assert.assertEquals("1,854.23", m);
	}

	@Test
	public void getFormatMoney() {
		String formatMoney = MoneyUtils.getFormatMoney(smallMoney, 2, 1);
		Assert.assertEquals("0.12元", formatMoney);
		formatMoney = MoneyUtils.getFormatMoney(bigMoney, 2, 1);
		Assert.assertEquals("213453.00元", formatMoney);
	}

	@Test
	public void getAccountantMoney() {
	}
}