package com.jutils.appender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class DesensitizeUtilsTest {

	private static final String TEST_STRING =
			"This is a test string contains phone number 13789011012 "
					+ "and a common number 123414. And a name=张三，\"李四四\"，李六六六，身份证号码 210491198009031416";

	@Test
	public void filterPhone() {
		String p1 = "这是一个测试的日志,包含一个 18911110000 电话号码和一个普通数字 1234";
		String out = DesensitizeUtils.filterPhone(p1);
		Assert.assertEquals("这是一个测试的日志,包含一个 189****0000 电话号码和一个普通数字 1234",out);
	}

	@Test
	public void filterIdentityId() {
		String id = "这段日志包含身份证信息，210491198009031416 和一串乱码 342013492381838242";
		String out = DesensitizeUtils.filterIdentityId(id);
		Assert.assertEquals("这段日志包含身份证信息，210491************ 和一串乱码 342013492381838242", out);
	}

	@Test
	public void filterName() {
		String name = "This is a name 段誉";
		String s = DesensitizeUtils.filterName(name);
		Assert.assertEquals("This is a name 段*", s);
		name = "慕容复";
		s = DesensitizeUtils.filterName(name);
		Assert.assertEquals("慕*", s);
	}

	@Test
	public void normalPerformanceTest() {
		long start = System.currentTimeMillis();
		runLoop();
		System.out.println("正常打印耗时 " + (System.currentTimeMillis() - start));
	}

	@Test
	public void logLoopPerformanceTest() {
		long start = System.currentTimeMillis();
		runLogLoop();
		System.out.println("log打印耗时 " + (System.currentTimeMillis() - start));
	}

	private void runLogLoop() {
		for (int i = 0; i < 10000; i++) {
			log.info(TEST_STRING);
		}
	}

	private void runLoop() {
		for (int i = 0; i < 10000; i++) {
			String test = DesensitizeUtils.filterName(TEST_STRING);
			test = DesensitizeUtils.filterPhone(test);
			test = DesensitizeUtils.filterIdentityId(test);
			System.out.println(test);
		}
	}

//	@Test
//	public void realLogFile() throws IOException {
//		try(BufferedReader br = new BufferedReader(new FileReader("/tmp/bunch.log"))) {
//			String line;
//			long start = System.currentTimeMillis();
//			while ((line = br.readLine()) != null) {
//				log.info(line);
//			}
//			System.out.println("耗时: " + (System.currentTimeMillis() - start));
//		}
//	}

}