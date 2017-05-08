package com.test.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestSort {
	
	public static void main(String[] args) {
////		Integer[] array = generateIntegerArray(170000,550000);
////		System.out.print("排序前数组---->");
////		for(int i = 0;i<array.length;i++){
////			System.out.print(array[i]+",");
////		}
////		insertionSortTest(array);
//		
//		Integer[] array1 = generateIntegerArray(3200000,5550000);
////		System.out.print("排序前数组---->");
////		for(int i = 0;i<array1.length;i++){
////			System.out.print(array1[i]+",");
////		}
//		shellSortTest(array1);
//		
//		Integer[] array2 = generateIntegerArray(3200000,5550000);
////		System.out.print("排序前数组---->");
////		for(int i = 0;i<array2.length;i++){
////			System.out.print(array2[i]+",");
////		}
//		mergeSortTest(array2);
		
		Integer[] arrayFast = generateIntegerArray(10,15);
		System.out.print("排序前数组---->");
		for(int i = 0;i < arrayFast.length;i++){
			System.out.print(arrayFast[i]+",");
		}
		fastSortTest(arrayFast);
	}
	
	public static void insertionSortTest(Integer[] array){
		Sort insetionSort = new InsertionSort();
		
		long startTime = System.currentTimeMillis();
		Integer[] result = insetionSort.doSort(array);
		long endTime = System.currentTimeMillis();
		System.out.println("");
		System.out.println("插入排序耗时===>"+(endTime-startTime)+"ms");
		
//		for(int i = 0;i<result.length;i++){
//			System.out.print(result[i]+",");
//		}
	}
	
	public static void shellSortTest(Integer[] array){
		Sort shellSort = new ShellSort();
		
		long startTime = System.currentTimeMillis();
		Integer[] result = shellSort.doSort(array);
		long endTime = System.currentTimeMillis();
		System.out.println("");
		System.out.println("希尔排序耗时===>"+(endTime-startTime)+"ms*****************************");
		
//		for(int i = 0;i<result.length;i++){
//			System.out.print(result[i]+",");
//		}
	}
	
	public static void mergeSortTest(Integer[] array){
		Sort mergeSort = new MergeSort();
		
		long startTime = System.currentTimeMillis();
		Integer[] result = mergeSort.doSort(array);
		long endTime = System.currentTimeMillis();
		System.out.println("");
		System.out.println("归并排序耗时===>"+(endTime-startTime)+"ms*****************************");
		
//		for(int i = 0;i<result.length;i++){
//			System.out.print(result[i]+",");
//		}
	}
	
	public static void fastSortTest(Integer[] array){
		Sort fastSort = new FastSort();
		
		long startTime = System.currentTimeMillis();
		Integer[] result = fastSort.doSort(array);
		long endTime = System.currentTimeMillis();
		System.out.println("");
		System.out.println("快速排序耗时===>"+(endTime-startTime)+"ms*****************************");
		
		for(int i = 0;i<result.length;i++){
			System.out.print(result[i]+",");
		}
	}
	
	/**
	 * 随机生成互异的数组
	 * @param n
	 * @param bound
	 * @return
	 */
	protected static Integer[] generateIntegerArray(int n,int bound){
		long startTime = System.currentTimeMillis();
		Integer[] array = new Integer[n];
		if(bound == 0) bound = 10;
		Random rd = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while(n > 0){
			int rdInt = rd.nextInt(bound);
			boolean isContain = set.add(rdInt);
			if(isContain){
				n--;
			}
		}
		array = set.toArray(array);
		long endTime = System.currentTimeMillis();
		
		Collections.shuffle(Arrays.asList(array));
		
		long shuffTime = System.currentTimeMillis();
		
		System.out.println("数组产生耗时="+(endTime-startTime)+"ms,乱序耗时="+(shuffTime - endTime)+"ms,数组长度="+(set.size()));
		return array;
	}

}
