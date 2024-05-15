package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordController {
	
	@GetMapping({"/list" })
	public String list() {
		return "list";
	}

	@GetMapping({"/word/new" })
	public String wordlist() {
		return "newword";
	}
	
	@GetMapping({"/book/edit" })
	public String booklist() {
		return "booklist";
	}

	@GetMapping({"/word/create" })
	public String wordcreate() {
		return "create";
	}
	
	@GetMapping({"/book/create" })
	public String bookcreate() {
		return "addbook";
	}
	
	@GetMapping({"/word/edit" })
	public String edit() {
		return "editword";
	}
	
}
