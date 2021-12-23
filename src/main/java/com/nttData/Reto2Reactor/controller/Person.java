package com.nttData.Reto2Reactor.controller;

import java.io.Serializable;

public class Person implements Serializable{

	private String name;
	
	private String user;
	
	private String password;
	
	private Integer age;

	public Person() {
		super();
	}

	public Person(String name, String user, String password, Integer age) {
		super();
		this.name = name;
		this.user = user;
		this.password = password;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
