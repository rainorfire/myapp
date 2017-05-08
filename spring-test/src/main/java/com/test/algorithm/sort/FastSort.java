package com.test.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 快速排序
 * @author cyj
 *
 */
public class FastSort implements Sort {

	public Integer[] doSort(Integer[] list) {
		doWork(list, 0, list.length - 1);
		return list;
	}
	
	protected void doWork(Integer[] list,int startIdx,int endIdx){
		if(startIdx < endIdx){
			//三数中值获取分隔数据
			int mIdx = getTerm(list,startIdx,endIdx);
			
			//将分割元与最后一个元素互调位置
			swap(list, mIdx, endIdx);
			
			int leftPos  = startIdx;
			int rightPos = endIdx - 1;
			int mVal = list[mIdx];
			
			for(int i = startIdx;i <= endIdx;i++){
				int leftVal = list[leftPos];
				int rightVal = list[rightPos];
				
				if(leftPos > rightPos && leftVal >= rightVal){
					swap(list, leftPos, endIdx);
					break;
				}
				
				if(leftVal <= mVal) leftPos++;
				if(rightVal >= mVal) rightPos--; 
				
				if(leftVal > mVal && rightVal < mVal){
					swap(list, leftPos, rightPos);
				}
			}
			
			doWork(list, startIdx, mIdx - 1);
			doWork(list, mIdx + 1, endIdx);
		}
	}
	
	protected int getTerm(Integer[] list,int startIdx,int endIdx){
		int startVal = list[startIdx];
		int endVal = list[endIdx];
		int middleVal = list[(startIdx + endIdx)/2];
		
		int tmpVal = Math.min(startVal, middleVal);
		int finalVal = Math.max(endVal, tmpVal);
		System.out.println("中值是："+finalVal);
		return ArrayUtils.indexOf(list, finalVal);
	}
	
	/**
	 * 交换数组中俩值
	 * @param list
	 * @param swapIdx
	 * @param swapedIdx
	 */
	protected void swap(Integer[] list,int swapIdx,int swapedIdx){
		int tmp = list[swapIdx];
		list[swapIdx] = list[swapedIdx];
		list[swapedIdx] = tmp;
	}

}
