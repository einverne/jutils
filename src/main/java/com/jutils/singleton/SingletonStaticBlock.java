package com.jutils.singleton;

/**
 * 饿汉，静态代码块初始化 instance
 *
 * 类初始化时 （静态变量、静态初始化块）>（变量、初始化块）>构造器
 */
public class SingletonStaticBlock {

	private static SingletonStaticBlock instance = null;

	static {
		instance = new SingletonStaticBlock();
	}

	private SingletonStaticBlock() {
		System.out.println("instance created");
	}

	public static SingletonStaticBlock getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		SingletonStaticBlock instance = SingletonStaticBlock.getInstance();
		SingletonStaticBlock instance1 = SingletonStaticBlock.getInstance();
	}
}
