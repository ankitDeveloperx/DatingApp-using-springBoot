package com.microsoft.dre.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microsoft.dre.entity.User;
import com.microsoft.dre.repository.UserRepository;
import com.microsoft.dre.util.UserGender;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repo;

	public User saveUser(User user) {
		return repo.save(user);
	}

	public Optional<User> findById(int id) {
		return repo.findById(id);
	}

	public List<User> findAllUsersByGender(UserGender og) {
		return repo.findByGender(og);
	}
	
}
