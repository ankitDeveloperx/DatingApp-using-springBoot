package com.microsoft.dre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.dre.entity.User;
import com.microsoft.dre.repository.UserRepository;
import com.microsoft.dre.responsestructure.ResponseStructure;
import com.microsoft.dre.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	
	@GetMapping("/best-match/{id}/{top}")
	public ResponseEntity<?> findBestMatch(@PathVariable int id, @PathVariable int top){
		return service.findBestMatch(id,top);
	}
	
	
}
