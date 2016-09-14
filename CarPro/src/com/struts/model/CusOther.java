package com.struts.model;

public class CusOther {
	private String user_name;
	private String user_password;
	private String user_mail;
	private int user_numbers;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public int getUser_numbers() {
		return user_numbers;
	}

	public void setUser_numbers(int user_numbers) {
		this.user_numbers = user_numbers;
	}

	public CusOther(String user_name, String user_password, String user_mail, int user_numbers) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_mail = user_mail;
		this.user_numbers = user_numbers;
	}

	public CusOther() {
		super();
	}

}
