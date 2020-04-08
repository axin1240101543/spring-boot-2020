package com.darren.center.springboot.utils;

/**
 *  倒序
 */
public class StringReverse {
	
	public static void main(String[] args) {
		String str = "abcdef";
		StringBuilder s = new StringBuilder(str);
		s.reverse();
		System.out.println(s.toString());
	}
	
}
