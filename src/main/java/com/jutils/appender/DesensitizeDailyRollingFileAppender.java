package com.jutils.appender;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.spi.LoggingEvent;

public class DesensitizeDailyRollingFileAppender extends DailyRollingFileAppender {

	@Override
	protected void subAppend(LoggingEvent event) {
		String message = (String) event.getMessage();
		String modifiedMessage = DesensitizeUtils.filterName(message);
		modifiedMessage = DesensitizeUtils.filterName(modifiedMessage);
		modifiedMessage = DesensitizeUtils.filterIdentityId(modifiedMessage);
		LoggingEvent modifiedEvent = new LoggingEvent(event.getFQNOfLoggerClass(), event.getLogger(), event.getTimeStamp(), event.getLevel(), modifiedMessage,
				event.getThreadName(), event.getThrowableInformation(), event.getNDC(), event.getLocationInformation(),
				event.getProperties());
		super.subAppend(event);
	}
}
