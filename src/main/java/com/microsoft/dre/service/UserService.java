package com.microsoft.dre.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microsoft.dre.dao.UserDao;
import com.microsoft.dre.entity.User;
import com.microsoft.dre.responsestructure.ResponseStructure;
import com.microsoft.dre.util.MatchingUser;
import com.microsoft.dre.util.SortByAgeDiffrence;
import com.microsoft.dre.util.UserGender;

@Service
public class UserService {

	@Autowired
	private UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		User savedUser = dao.saveUser(user);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Saved Successfully").body(savedUser).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re;
	}

	public ResponseEntity<?> findBestMatch(int id, int top) {

		Optional<User> optional = dao.findById(id);

		if (optional.isEmpty()) {
			throw new RuntimeException("Unable to get top matches becouse invalid user id");
		}

		User user = optional.get();

		UserGender gender = user.getGender();

		int userAge = user.getAge();

		List<String> userIntrests = user.getIntrests();

		UserGender og = gender.equals(UserGender.MALE) ? UserGender.FEMALE : UserGender.MALE;

		List<User> users = dao.findAllUsersByGender(og);

		List<MatchingUser> matchingUsers = new ArrayList<>();

		for (User u : users) {
			MatchingUser mu = new MatchingUser();
			mu.setId(u.getId());
			mu.setName(u.getName());
			mu.setEmail(u.getEmail());
			mu.setPhone(u.getPhone());
			mu.setGender(u.getGender());
			mu.setAge(u.getAge());
			mu.setIntrests(u.getIntrests());
			mu.setPassword(u.getPassword());
			mu.setAgeDiffrence(Math.abs(userAge - u.getAge()));
			int mic = 0;
			List<String> ui = u.getIntrests();
			for (String s : userIntrests) {
				if (ui.contains(s))
					mic++;
			}
			mu.setMatchingIntrestsCount(mic);
			matchingUsers.add(mu);
		}

		Collections.sort(matchingUsers, new SortByAgeDiffrence());

		matchingUsers = matchingUsers.stream().limit(top).collect(Collectors.toList());

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Top Matching User Found Successsfully").body(matchingUsers).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re;
	}

	private void printCollection(Collection c) {
		for (Object o : c) {
			System.out.println(o);
		}
	}

}
