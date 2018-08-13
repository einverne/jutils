package com.jutils.invocation;

import java.lang.reflect.Proxy;

/**
 *  XXProxy 代理者
 */
public class VehicalProxy {

	private IVehical vehical;

	public VehicalProxy(IVehical v) {
		this.vehical = v;
	}

	/**
	 * Proxy.newProxyInstance() 方法会返回一个代理对象类实例
	 * newProxyInstance() 方法有三个参数
	 *
	 * <li>被代理类原接口实现的类加载器 classLoader</li>
	 * <li>被代理类实现的所有接口</li>
	 * <li>处理者 Handler</li>
	 *
	 * @return 代理对象实例
	 */
	public IVehical create() {
		final Class<?>[] interfaces = new Class[]{IVehical.class};
		final VehicalInvocationHandler handler = new VehicalInvocationHandler(vehical);

		return (IVehical) Proxy
			.newProxyInstance(IVehical.class.getClassLoader(), interfaces, handler);
	}
}
