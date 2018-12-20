package com.jutils.appender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class DesensitizeUtilsTest {

	private static final String TEST_STRING =
			"This is a test string contains phone number 13789011012"
					+ "and a common number 123414. And a name=张三，\"李四四\"，李六六六，身份证号码 210491198009031416";

	@Test
	public void filterPhone() {
		String p1 = "这是一个测试的日志 包含一个 18921110000 电话号码";
		String out = DesensitizeUtils.filterPhone(p1);
		log.info("output 电话号码:" + out);
	}

	@Test
	public void filterIdentityId() {
		String id = "210491198009031416";
		String s = DesensitizeUtils.filterIdentityId(id);
		log.info("output 身份证" + s);
	}

	@Test
	public void filterName() {
		String name = "黄你好";
		String s = DesensitizeUtils.filterName(name);
		log.info("output name " + s);
		name = "张三";
		s = DesensitizeUtils.filterName(name);
		log.info("output name " + s);
		name = "慕容那那";
		s = DesensitizeUtils.filterName(name);
		log.info("output name " + s);
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

	@Test
	public void realLogFile() throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("/tmp/jutils/10000.log"))) {
			String line;
			long start = System.currentTimeMillis();
			while ((line = br.readLine()) != null) {
				log.info(line);
			}
			System.out.println("耗时: " + (System.currentTimeMillis() - start));
		}
	}
}