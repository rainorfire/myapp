package com.jvm.classload;

public class TestMain {

	public static void main(String[] args) {
		/**
		 * 1、对于静态字段的引用，只会触发该字段所在类的初始化
		 * 2、对于final 和 static 共同修饰的基本类型和string型字段，会在类加载过程中（类初始化之前）赋值。
		 * 		SubClass.val值已经在TestMain编译时，已经存储在了TestMain常量池里，所以调用SubClass.val实际是从自己常量池里 取值
		 */
		System.out.println(SubClass.val);
	}

}
