package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_id")
	private Integer categoryId;
	
	
	private String word;
	private String body;
	private Integer remember;
	
	public Blogs() {
		
	}
	
	public Blogs(Integer id, Integer categoryId, String word, String body,Integer remember ) {
		this.id = id;
		this.categoryId =categoryId;
		this.word = word;
		this.body = body;
		this.remember=remember;
	
	}
	public Blogs(Integer id, Integer categoryId, String word, String body ) {
		this.id = id;
		this.categoryId =categoryId;
		this.word = word;
		this.body = body;
		this.remember=0;
	
	}
	
	public Blogs( Integer categoryId,String word, String body) {
		this.categoryId =categoryId;
		this.word = word;
		this.body = body;
		this.remember=0;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getWord() {
		return word;
	}

	public String getBody() {
		return body;
	}

	public Integer getRemember() {
		return remember;
	}
}
