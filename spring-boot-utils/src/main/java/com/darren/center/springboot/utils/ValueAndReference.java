package com.darren.center.springboot.utils;

import com.darren.center.springboot.entity.User;

/**
 * 只有对象才会变化
 */
public class ValueAndReference {
	
	private static int i = 10;
	
	private static String str = "123";
	
	private static void updateValue(int i) {
		i = i * 10;
	}
	
	private static void updateValueStr(String str) {
		str = str + "456";
	}
	
	private static void updateValueEntity(User user) {
		user.setId(2);
		user.setUsername("lisi");
	}
	
	public static void main(String[] args) {
		System.out.println("before:" + i);
		updateValue(i);
		System.out.println("after:" + i);
		
		System.out.println("before str:" + str);
		updateValueStr(str);
		System.out.println("after str:" + str);
		
		User user = new User(1, "zhangsan", 1);
		System.out.println("before Entity:" + user);
		updateValueEntity(user);
		System.out.println("after Entity:" + user);
	}
	

}
