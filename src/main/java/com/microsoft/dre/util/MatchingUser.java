package com.microsoft.dre.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MatchingUser {
	private int id;
	private String name;
	private String email;
	private long phone;
	private String password;
	private UserGender gender;
	private int age;
	private List<String> intrests;
	private int ageDiffrence;
	private int matchingIntrestsCount;
}
