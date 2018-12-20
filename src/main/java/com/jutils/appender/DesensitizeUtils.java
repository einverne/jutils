package com.jutils.appender;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class DesensitizeUtils {

	/**
	 * * 正则：手机号 * <p> 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
	 * </p>
	 * * <p>联通：130、131、132、145、155、156、175、176、185、186、166</p> * <p>电信：133、153、173、177、180、181、189、199</p>
	 */
	private static final String PHONE_REGEX = "((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}";
	/**
	 * 身份证正则
	 */
	private static final String IDENTITY_REGEX = "[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})";
	/**
	 * Unicode 基本汉字 	4E00-9FA5 匹配2到4字汉字
	 *
	 * 为了尽量不误伤，针对性匹配，只匹配 name=XXX, 或者 "XXX 的中文名字
	 */
	private static final String CHINESE_NAME_REGEX = "[\u4E00-\u9FA5]{2,4}";

	private static final String ALL_REGEX =
			"(" + PHONE_REGEX + "|" + IDENTITY_REGEX + "|" + CHINESE_NAME_REGEX + ")";

	private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
	private static final Pattern IDENTITY_PATTERN = Pattern.compile(IDENTITY_REGEX);
	private static final Pattern CHINESE_NAME_PATTERN = Pattern.compile(CHINESE_NAME_REGEX);
	private static final Pattern ALL_PATTERN = Pattern.compile(ALL_REGEX);

	public static String filterAll(String message) {
		if (StringUtils.isEmpty(message)) {
			return "";
		}
		return ALL_PATTERN.matcher(message).replaceAll("****");
	}

	public static String filterPhone(String message) {
		if (StringUtils.isEmpty(message)) {
			return message;
		}
		return PHONE_PATTERN.matcher(message).replaceAll("********");
	}

	public static String filterIdentityId(String message) {
		if (StringUtils.isEmpty(message)) {
			return message;
		}
		return IDENTITY_PATTERN.matcher(message).replaceAll("******************");
	}

	public static String filterName(String message) {
		if (StringUtils.isEmpty(message)) {
			return message;
		}
		return CHINESE_NAME_PATTERN.matcher(message).replaceAll("***");
	}
}
