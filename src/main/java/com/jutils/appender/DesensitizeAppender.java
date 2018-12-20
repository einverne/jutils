package com.jutils.appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class DesensitizeAppender extends AppenderSkeleton {

	@Override
	protected void append(LoggingEvent loggingEvent) {
		System.out.println(loggingEvent.toString());
		System.out.println(loggingEvent.getMessage());
		System.out.println(loggingEvent.getRenderedMessage());
		System.out.println(loggingEvent.getLevel());
	}

	/**
	 * 当框架即将要删除您的自定义 appender 实例时，它会调用您的 appender 的 close() 方法。 close() 是一个清理方法，意味着
	 * 您需要释放已分配的所有资源。它是一个必需的方法，并且不接受任何参数。它必须把 closed 字段设置为 true ，并在有人尝试使用关闭的 appender 时向框架发出警报。
	 */
	@Override
	public void close() {
		if (this.closed) {
			return;
		}
		this.closed = true;
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}
}
