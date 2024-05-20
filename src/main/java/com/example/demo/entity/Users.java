package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	private String name;
	private String password;
	public Users() {
		
	}
	
	public Users(String name, String email, String password ) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public Integer getId() {
		return id;	
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;	
	}
	
	public String getPassword() {
		return password;
	}
	
}
