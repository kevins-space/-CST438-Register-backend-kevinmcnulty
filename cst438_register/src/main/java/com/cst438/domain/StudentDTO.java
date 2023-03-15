package com.cst438.domain;

public class StudentDTO {
	
	private int student_id;
	private String name;
	private String email;
	private int statusCode;
	
	public StudentDTO() {
		super();
	}
	
	public StudentDTO(int student_id, String name, String email, int statusCode) {
		super();
		this.student_id = student_id;
		this.name = name;
		this.email = email;
		this.statusCode = statusCode;
	}
	
	public StudentDTO(String name, String email, int statusCode, int student_id) {
		super();
		this.student_id = student_id;
		this.name = name;
		this.email = email;
		this.statusCode = statusCode;
	}
	
	public int getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}