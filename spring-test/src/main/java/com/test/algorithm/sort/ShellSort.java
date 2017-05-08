package com.test.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 希尔排序
 * @author cyj
 *
 *<b>From baidu</b>
 *<pre>
 *先取一个小于n的整数d1作为第一个增量，把文件的全部记录分组。所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；
 *然后，取第二个增量d2<d1重复上述的分组和排序，直至所取的增量  =1(  <  …<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。
 *该方法实质上是一种分组插入方法
 *</pre>
 */
public class ShellSort implements Sort {

	public Integer[] doSort(Integer[] list) {
		Integer[] arrayH = generateIncrementVal(list.length);
		for(int i =0 ; i < arrayH.length; i++){
			int gap = arrayH[i];
			for(int h = gap;h < list.length; h++){
				for(int k = h;k >= gap && list[k].intValue() < list[k-gap].intValue();k -= gap){
					int tmp = list[k-gap];
					list[k-gap] = list[k];
					list[k] = tmp;
				}
			}
		}
		return list;
	}
	
	protected Integer[] generateIncrementVal(int length){
		List<Integer> list = new ArrayList<Integer>();
		int val = length / 2;
		while(val >= 1){
			list.add(val);
			val = val / 2;
		}
		return list.toArray(new Integer[list.size()]);
	}
}
