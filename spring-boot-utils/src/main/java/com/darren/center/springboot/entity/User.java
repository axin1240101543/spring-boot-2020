package com.darren.center.springboot.entity;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -628781310907098541L;

	private Integer id;

	private String username;

	private Integer age;

	public User() {
		super();
	}

	public User(Integer id, String username, Integer age) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age + "]";
	}

	@Override
	public int compareTo(User user) {
		return user.age - this.age;
	}

}
