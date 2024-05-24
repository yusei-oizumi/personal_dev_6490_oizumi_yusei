package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Blogs;

public interface BooksRepository extends JpaRepository<Blogs, Integer>{
	
	List<Blogs> findByCategoryId(Integer CategoryId);

	List<Blogs> findAllByOrderByIdAsc();

	List<Blogs> findByCategoryIdOrderByIdAsc(Integer categoryId);
	

}
