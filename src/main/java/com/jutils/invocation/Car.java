package com.jutils.invocation;

public class Car implements IVehical {

	@Override
	public void run() {
		System.out.println("Car is running");
	}


	public static void main(String[] args) {
		IVehical car = new Car();
		VehicalProxy proxy = new VehicalProxy(car);

		IVehical proxyObj = proxy.create();
		proxyObj.run();
	}
}
