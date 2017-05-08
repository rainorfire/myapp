package com.test.algorithm;

/**
 * 斐波拉契数列 递归实现
 * @author cyj
 *
 */
public class FibonacciRecursionFun {
	
	public Long recursionFun(Long n){
		if(n == 0 || n == 1){
			return 1L;
		}else{
			return recursionFun(n-1)+recursionFun(n-2);
		}
	}
	
	public static void main(String[] args) {
		Long num = 43L;
		FibonacciRecursionFun fbncRecursion = new FibonacciRecursionFun();
		long startTime = System.currentTimeMillis();
		Long fbncNum = fbncRecursion.recursionFun(num);
		long endTime = System.currentTimeMillis();
		
		"".hashCode();
		System.out.println("递归实现获取第"+(num+1)+"个斐波拉契数耗时----->"+(endTime-startTime)+"ms，此数为="+fbncNum);
	}
}
