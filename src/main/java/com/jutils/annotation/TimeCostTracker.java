package com.jutils.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TimeCostTracker {

	@Check
	private int var;

	public TimeCostTracker(int var) {
		this.var = var;
	}

	public static void main(String[] args)
		throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
		TimeCostTracker timeCostTracker = new TimeCostTracker(100);
		Class<? extends TimeCostTracker> aClass = timeCostTracker.getClass();
		Field[] declaredFields = aClass.getDeclaredFields();
		for (Field f : declaredFields) {
			System.out.println("field " + f.getName());
		}
		Method[] declaredMethods = aClass.getDeclaredMethods();
		for (Method m : declaredMethods) {
			if (m.isAnnotationPresent(TimeCost.class)) {
				m.setAccessible(true);
				System.out.println("TimeCost start " + System.currentTimeMillis());
				m.invoke(timeCostTracker, null);
				System.out.println("TimeCost end " + System.currentTimeMillis());
			} else {
				int parameterCount = m.getParameterCount();
				if (parameterCount <= 0) {
					m.invoke(timeCostTracker, null);
				} else if (parameterCount <= 1) {
					Class<?>[] parameterTypes = m.getParameterTypes();
					if (parameterTypes[0] == int.class) {
						m.invoke(timeCostTracker, 50);
					}
				}
			}
		}

		try {
			Method printVar = aClass.getDeclaredMethod("printVar");
			printVar.setAccessible(true);
			printVar.invoke(timeCostTracker);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@TimeCost(enabled = true)
	public int calculate() {
		int sum = 0;
		try {
			for (int i = 0; i < var; i++) {
				sum += i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sum;
	}

	public void printVar() {
		System.out.println("var value : " + var);
	}

	public int getVar() {
		return var;
	}

	public void setVar(int var) {
		this.var = var;
	}
}
