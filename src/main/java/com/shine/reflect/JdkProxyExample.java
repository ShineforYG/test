package com.shine.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler {

	private Object target = null;

	public Object bind(Object target) {
		this.target = target;
		Object o  = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
		return o;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("1");
		System.out.println("2");
		Object obj = method.invoke(target, args);
		System.out.println("3");
		return obj;
	}
}