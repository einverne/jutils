package com.jutils.invocation;

import java.lang.reflect.Proxy;

public class VehicalProxy {

	private IVehical vehical;

	public VehicalProxy(IVehical v) {
		this.vehical = v;
	}

	public IVehical create() {
		final Class<?>[] interfaces = new Class[]{IVehical.class};
		final VehicalInvocationHandler handler = new VehicalInvocationHandler(vehical);

		return (IVehical) Proxy
			.newProxyInstance(IVehical.class.getClassLoader(), interfaces, handler);
	}
}
