package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	

	
	@Autowired
	HttpSession session;

	@GetMapping({"/", "/login", "/logout" })
	public String index() {
		session.invalidate();
		return "login";
	}
	
	@PostMapping({"/login" })
	public String login(
		@RequestParam("email") String email,
		@RequestParam("password") String password,
		Model model) {
			

		return "list";
			
		}
	
	
	
	@GetMapping({"/users/new" })
	public String register() {
		
		return "user";
	}
	
	@PostMapping({"/users/add" })
	public String add() {
	
		return "";
}
	
}
