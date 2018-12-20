package com.jutils.appender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class DesensitizeAppenderWrapper extends AppenderSkeleton implements AppenderAttachable {

	private final List<Appender> appenders = new ArrayList<Appender>();

	@Override
	public void close() {
		synchronized (appenders) {
			for (Appender appender : appenders) {
				appender.close();
			}
		}
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	public void addAppender(Appender appender) {
		synchronized (appenders) {
			appenders.add(appender);
		}
	}

	@Override
	public Enumeration getAllAppenders() {
		return Collections.enumeration(appenders);
	}

	@Override
	public Appender getAppender(String name) {
		synchronized (appenders) {
			for (Appender appender : appenders) {
				if (appender.getName().equals(name)) {
					return appender;
				}
			}
		}
		return null;
	}

	@Override
	public boolean isAttached(Appender appender) {
		synchronized (appenders) {
			for (Appender wrapped : appenders) {
				if (wrapped.equals(appender)) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public void removeAllAppenders() {
		synchronized (appenders) {
			appenders.clear();
		}
	}

	@Override
	public void removeAppender(Appender appender) {
		synchronized (appenders) {
			for (Iterator<Appender> i = appenders.iterator(); i.hasNext(); ) {
				if (i.next().equals(appender)) {
					i.remove();
				}
			}
		}
	}

	@Override
	public void removeAppender(String name) {
		synchronized (appenders) {
			for (Iterator<Appender> i = appenders.iterator(); i.hasNext(); ) {
				if (i.next().getName().equals(name)) {
					i.remove();
				}
			}
		}
	}

	@Override
	protected void append(LoggingEvent event) {
		String message = (String) event.getMessage();
		String modifiedMessage = DesensitizeUtils.filterName(message);
		modifiedMessage = DesensitizeUtils.filterName(modifiedMessage);
		modifiedMessage = DesensitizeUtils.filterIdentityId(modifiedMessage);
		LoggingEvent modifiedEvent = new LoggingEvent(event.getFQNOfLoggerClass(), event.getLogger(),
				event.getTimeStamp(), event.getLevel(), modifiedMessage,
				event.getThreadName(), event.getThrowableInformation(), event.getNDC(),
				event.getLocationInformation(),
				event.getProperties());

		synchronized (appenders) {
			for (Appender appender : appenders) {
				appender.doAppend(modifiedEvent);
			}
		}
	}

}
