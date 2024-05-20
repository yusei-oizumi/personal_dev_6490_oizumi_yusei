package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

//import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	Account account;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HttpSession session;

	@GetMapping({"/", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		
		session.invalidate();
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}
		
		return "login";
	}
	
	@PostMapping({"/login" })
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		
		if (email.length() == 0 || password.length() == 0) {
				model.addAttribute("message", "入力してください");
				return "login";
		}
		
		List<Users> userList = userRepository.findByEmailAndPassword(email, password);
		if (userList == null || userList.size() == 0) {
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			model.addAttribute("email", email);
			return "login";
		}
		
		
		Users user = userList.get(0);

		account.setName(user.getName());

		return "list";
			
		}
	
	
	
	@GetMapping({"/users/new" })
	public String register() {
		
		return "user";
	}
	
	@PostMapping({"/users/add" })
	public String add(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "repassword", defaultValue = "") String repassword, 
			Model model) {
		
		List<String> errorList = new ArrayList<>();
		if (name.length() == 0) {
			errorList.add("名前は必須です");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}
		if (repassword.length() == 0) {
			errorList.add("パスワード（確認）は必須です");
		}
		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		}
		
		if (!password.equals(repassword)) {
			errorList.add("パスワードが一致していません");
		}
		
		List<Users> userList = userRepository.findByEmailAndPassword(email,password);
		if (userList != null && userList.size() > 0) {
			errorList.add("登録済みのメールアドレスです");
		}
		
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			model.addAttribute("repassword", repassword);
			return "user";
		}
		
		Users user = new Users(name, email, password);
		userRepository.save(user);
		
		return "login";
}
	
}
