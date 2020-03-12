package com.darren.center.springboot.utils;

/**
 *  倒序
 *  去重
 *  排序
 */
public class StringReverse {
	
	public static void main(String[] args) {
		String str = "abcdef";
		StringBuilder s = new StringBuilder(str);
		s.reverse();
		System.out.println(s.toString());
		
		int[] array = {1, 2, 5, 4, 3, 10 ,9, 10, 4, 5};
		int[] myResult = notRepect(array);
		myResult = sort(myResult);
		for(int i = 0; i < myResult.length; i++) {
			System.out.print(myResult[i] + " ");
		}
	}
	
	
	public static int[] notRepect(int[] array) {
		int[] temArray = new int[array.length];
		int t = 0;
		for(int i = 0; i < array.length; i++) {
			boolean isTrue = true;
			for(int j = i+1; j < array.length; j++) {
				if(array[i] == array[j]) {
					isTrue = false;
					break;
				}
			}
			if(isTrue) {
				temArray[t] = array[i];
				t++;
			}
		}
		int[] newArray = new int[t];
		System.arraycopy(temArray, 0, newArray, 0, t);
		return newArray;
	} 
	
	
	public static int[] sort(int[] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				if(array[i] < array[j]) {
					int a = array[i];
					array[i] = array[j];
					array[j] = a;
				}
			}
		}
		return array;
	}
	
}
