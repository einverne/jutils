package com.jutils.invocation;

public class Car implements IVehical {

	public static void main(String[] args) {
		IVehical car = new Car();
		VehicalProxy proxy = new VehicalProxy(car);

		/**
		 * 代理者通常与其代理的对象拥有相同的接口，代理对调用者完全透明，调用者
		 * 只关心接口不关心实现
		 *
		 * 目的就是通过代理以控制某个对象的访问。当通过使用代理（代理实现InvocationHandler接口）调用某个方法时，会自动调用代理的invoke方法，相当于拦截机制。因此可以在代理中封装逻辑，比如访问控制、远程通信、日志、缓存等等
		 */
		IVehical proxyObj = proxy.create();

		/**
		 *
		 对 IVehical 接口的调用会交由 Proxy invoke 方法处理，在不改变 run() 代码的前提下，
		 增加动态逻辑
		 */
		proxyObj.run();
	}

	@Override
	public void run() {
		System.out.println("Car is running");
	}
}
