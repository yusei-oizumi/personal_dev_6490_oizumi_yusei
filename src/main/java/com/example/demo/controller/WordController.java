package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Blogs;
import com.example.demo.entity.Categories;
import com.example.demo.model.Category;
import com.example.demo.repository.BooksRepository;
import com.example.demo.repository.CategoriesRepository;

@Controller
public class WordController {
	
	@Autowired
	Category category;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@Autowired
	BooksRepository booksRepository;
	
	@GetMapping({"/list" })
	public String list(Model model) {
		List<Categories> categoryList = categoriesRepository.findAll();
		model.addAttribute("category", categoryList);
		
		return "booklist";
	}
	

	@GetMapping({"/word/{id}/list" })
	public String wordlist(
			@PathVariable("id") Integer id,
			Model model) {
		Categories words = categoriesRepository.findById(id).get();
		
		model.addAttribute("word", words);
		category.setCategoryId(id);
		
		return "wordlist";
	}
	
	@PostMapping({"/word/{id}/edit"})
	public String editword(
			@PathVariable("id") Integer id,
			@RequestParam(name = "word", defaultValue = "") String word,
			@RequestParam(name = "body", defaultValue = "") String body,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		Blogs  wordedit = new Blogs(id, categoryId, word, body);
		booksRepository.save(wordedit);
		return "redirect:/word/list";
	}
	
	@PostMapping({"/word/create"})
	public String newword(
			@RequestParam(name = "word", defaultValue = "") String word,
			@RequestParam(name = "body", defaultValue = "") String body,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		
		Blogs newword = new Blogs(word, body);
		booksRepository.save(newword);
		return "redirect:/word/list";
	}
	
	@GetMapping({"/book/edit" })
	public String booklist() {
		return "booklist";
	}
	
	@PostMapping({"/book/edit" })
	public String newbook(
			@RequestParam(name = "book", defaultValue = "") String book,
			Model model) {
		Categories category = new Categories(book);
		categoriesRepository.save(category);
		
		return "redirect:/list";
	}
	
	@PostMapping({"/book/{id}/delete" })
	public String bookdelete(@PathVariable("id") Integer id) {
		categoriesRepository.deleteById(id);
		return "redirect:/list";
	}

	@GetMapping({"/word/create" })
	public String wordcreate() {
		return "wordcreate";
	}
	
	@GetMapping({"/book/create" })
	public String bookcreate() {
		return "addbook";
	}
	
	@GetMapping({"/word/{id}/edit" })
	public String edit(@PathVariable("id") Integer id, Model model) {
		Blogs word = booksRepository.findById(id).get();
		model.addAttribute("word", word);
		return "editword";
	}
	
}
