package com.test.algorithm.sort;

import java.util.List;

/**
 * 插入排序实现
 * @author cyj
 *</br>
 *<b>From baidu:</b>
 *<pre>
 *有一个已经有序的数据序列，要求在这个已经排好的数据序列中插入一个数，但要求插入后此数据序列仍然有序，这个时候就要用到一种新的排序方法——插入排序法,
 *插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，算法适用于少量数据的排序，时间复杂度为O(n^2)。是稳定的排序方法。
 *插入算法把要排序的数组分成两部分：第一部分包含了这个数组的所有元素，但将最后一个元素除外（让数组多一个空间才有插入的位置），而第二部分就只包含这一个元素（即待插入元素）。
 *在第一部分排序完成后，再将这个最后元素插入到已排好序的第一部分中。
 *插入排序的基本思想是：每步将一个待排序的纪录，按其关键码值的大小插入前面已经排序的文件中适当位置上，直到全部插入完为止。
 *O(N^2) 
 *</pre>
 */
public class InsertionSort implements Sort{

	public Integer[] doSort(Integer[] list) {
		for(int i=1;i<list.length;i++){
			for(int j = i;j > 0;j--){
				Integer afterObj = list[j];
				Integer preObj = list[j-1];
				if(afterObj.intValue() < preObj.intValue()){
					Integer tmp = preObj;
					list[j-1] = afterObj;
					list[j] = tmp;
				}
			}
		}
		return list;
	}

}
