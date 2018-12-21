package com.jutils.appender;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogLevelATest {

	@Test
	public void logLevelTest() {
		log.debug("this is a debug log");
		log.info("this is a info log");
		log.warn("this is a warn log");
		log.error("this is a error log");
	}
}
