package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Blogs;
import com.example.demo.repository.BooksRepository;

@Controller
public class QuizController {
	
	@Autowired
	BooksRepository booksRepository;
	
	@GetMapping({"/quiz"})
	public String quiz(
			@RequestParam(name="categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		List<Blogs> quizmenu = booksRepository.findByCategoryIdOrderByIdAsc(categoryId);
		model.addAttribute("blog", quizmenu);
		return "quiz";
	}

}
