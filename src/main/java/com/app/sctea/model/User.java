package com.app.sctea.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.firebase.database.annotations.NotNull;

public class User {
	@NotNull
	private String id;
	@NotNull
	private String email;
	@NotNull
	private String name;
	@NotNull
	private String password;
	
	
	public User() {
	}
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	public void setEmail(String emailInput) {
		this.email = emailInput; 
	}
	public String getEmail() {
		return email;
	}
	public void setName(String nameInput) {
		this.name = nameInput;
	}
	public String getName() {
		return name;
	}
	public void setPassword(String passwordInput) {
		this.password = passwordInput;
	}
	public String getPassword() {
		return password;
	}
	
}
