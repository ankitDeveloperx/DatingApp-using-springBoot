package com.microsoft.dre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsoft.dre.entity.User;
import com.microsoft.dre.util.UserGender;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGender(UserGender og);

}
