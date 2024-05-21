package com.example.demo.controller;

import java.util.ArrayList;
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

	@GetMapping({ "/list" })
	public String list(Model model) {
		List<Categories> categoryList = categoriesRepository.findAll();
		model.addAttribute("category", categoryList);

		return "booklist";
	}

	@GetMapping({ "/word/{id}/list" })
	public String wordlist(
			@PathVariable("id") Integer id,
			@RequestParam(name="idAsc",defaultValue = "")String idAsc,
			Model model) {
		Categories words = categoriesRepository.findById(id).get();
		List<Blogs> blogs = booksRepository.findAllByOrderByIdAsc();
		
		model.addAttribute("word", words);
		model.addAttribute("blog", blogs);
		category.setCategoryId(id);

		return "wordlist";
	}

	@PostMapping({ "/word/{id}/edit" })
	public String editword(
			@PathVariable("id") Integer id,
			@RequestParam(name = "word", defaultValue = "") String word,
			@RequestParam(name = "body", defaultValue = "") String body,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		Blogs wordedit = new Blogs(id, categoryId, word, body);
		booksRepository.save(wordedit);
		return "redirect:/word/" + categoryId + "/list";
	}
	
	@PostMapping({ "/word/{id}/check" })
	public String checkword(
			@PathVariable("id") Integer id,
			@RequestParam(name = "word", defaultValue = "") String word,
			@RequestParam(name = "body", defaultValue = "") String body,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "remember", defaultValue = "") Integer remember,
			Model model) {
		Blogs wordcheck = booksRepository.findById(id).get();
		wordcheck = new Blogs(id, categoryId, word, body,remember);
		booksRepository.save(wordcheck);
		return "redirect:/word/" + categoryId + "/list";
	}

	@PostMapping({ "/word/create" })
	public String newword(
			@RequestParam(name = "word", defaultValue = "") String word,
			@RequestParam(name = "body", defaultValue = "") String body,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		List<String> errorList = new ArrayList<>();
		if (word.length() == 0) {
			errorList.add("単語の入力は必須です");
		}
		if (body.length() == 0) {
			errorList.add("詳細の入力は必須です");
		}

		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			
			model.addAttribute("categoryId", categoryId);
			return "wordcreate";
		}
		model.addAttribute("word", word);
		model.addAttribute("body", body);

		Blogs newword = new Blogs(categoryId, word, body);
		booksRepository.save(newword);

		if(categoryId == null) {
			return "redirect:/list";
		}
		return "redirect:/word/" + categoryId + "/list";
	}

	@GetMapping({ "/book/edit" })
	public String booklist() {
		return "booklist";
	}

	@PostMapping({ "/book/edit" })
	public String newbook(
			@RequestParam(name = "book", defaultValue = "") String book,
			Model model) {

		if (book.equals("")) {
			model.addAttribute("message", "入力は必須です");
			return "addbook";
		}

		Categories category = new Categories(book);
		Categories newcategories = categoriesRepository.save(category);
		Blogs newword = new Blogs(newcategories.getId(), "Sample", "例");
		booksRepository.save(newword);

		return "redirect:/list";

	}

	@PostMapping({ "/book/{id}/delete" })
	public String bookdelete(@PathVariable("id") Integer id) {
		categoriesRepository.deleteById(id);
		return "redirect:/list";
	}

	@PostMapping({ "/word/{id}/delete" })
	public String worddelete(@PathVariable("id") Integer id,
			@RequestParam("categoryId") Integer categoryId) {
		booksRepository.deleteById(id);
		return "redirect:/word/" + categoryId + "/list";
	}

	@GetMapping({ "/word/create" })
	public String wordcreate(@RequestParam("categoryId") Integer categoryId,
			Model model) {
		model.addAttribute("categoryId", categoryId);
		return "wordcreate";
	}

	@GetMapping({ "/book/create" })
	public String bookcreate() {
		return "addbook";
	}

	@GetMapping({ "/word/{id}/edit" })
	public String edit(@PathVariable("id") Integer id, Model model) {
		Blogs word = booksRepository.findById(id).get();
		model.addAttribute("word", word);
		return "editword";
	}

}
