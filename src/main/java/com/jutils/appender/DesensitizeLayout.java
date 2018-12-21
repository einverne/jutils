package com.jutils.appender;

import java.lang.reflect.Field;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class DesensitizeLayout extends PatternLayout {

	@Override
	public String format(LoggingEvent event) {
		if (event.getMessage() != null && event.getMessage() instanceof String) {
			String message = event.getMessage().toString();
			message = DesensitizeUtils.filterIdentityId(message);
			message = DesensitizeUtils.filterPhone(message);
			message = DesensitizeUtils.filterName(message);
			try {
				Field msgField = LoggingEvent.class.getDeclaredField("message");
				msgField.setAccessible(true);
				msgField.set(event, message);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return super.format(event);
	}
}
