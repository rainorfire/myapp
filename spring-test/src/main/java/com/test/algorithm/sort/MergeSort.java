package com.test.algorithm.sort;

/**
 * 归并排序
 * @author cyj
 * <br/>
 *<pre>
 *可以将A，B组各自再分成二组。
 *依次类推，当分出来的小组只有一个数据时，可以认为这个小组组内已经达到了有序，
 *然后再合并相邻的二个小组就可以了。
 *这样通过先递归的分解数列，再合并数列就完成了归并排序。
 *O(N*logN)
 *</pre>
 */
public class MergeSort implements Sort {

	public Integer[] doSort(Integer[] list) {
		int length = list.length;
		Integer[] tmpArray = new Integer[length];
		mergeSort(list, tmpArray, 0, length - 1);
		return list;
	}
	
	protected void mergeSort(Integer[] list,Integer[] tmpArray,int left,int right){
		if(left < right){
			int center = (left + right)/2;
			mergeSort(list, tmpArray, left, center);
			mergeSort(list, tmpArray, center + 1, right);
			doMerge(list, tmpArray, left, center, center + 1, right);
		}
	}
	
	protected void doMerge(Integer[] list,Integer[] tmpArray,int leftPos,int leftEnd,int rightPos,int rightEnd){
		int tmpPos = leftPos;
		int nums = rightEnd - leftPos + 1;
		while(leftPos <= leftEnd && rightPos <= rightEnd){
			int leftVal  = list[leftPos];
			int rightVal = list[rightPos];
			if(leftVal <= rightVal){
				tmpArray[tmpPos] = leftVal;
				leftPos++;
			}else{
				tmpArray[tmpPos] = rightVal;
				rightPos++;
			}
			tmpPos += 1;
		}
		
		while(leftPos <= leftEnd){
			int leftVal  = list[leftPos];
			tmpArray[tmpPos] = leftVal;
			leftPos++;
			tmpPos += 1;
		}
		
		while(rightPos <= rightEnd){
			int rightVal = list[rightPos];
			tmpArray[tmpPos] = rightVal;
			rightPos++;
			tmpPos += 1;
		}
		
		for(int i = 0;i < nums; i++,rightEnd--){
			list[rightEnd] = tmpArray[rightEnd];
		}
	}

}
