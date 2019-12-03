package com.shine.reflect;

public class ProxyTest {

	public static void main(String[] args) {
		testJdkProxy();
	}

	public static void testJdkProxy() {
		JdkProxyExample jdk = new JdkProxyExample();
		HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
//		proxy.sayHelloWorld();
		proxy.say();
	}

}
