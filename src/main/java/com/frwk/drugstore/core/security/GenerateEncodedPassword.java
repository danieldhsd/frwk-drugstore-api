package com.frwk.drugstore.core.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateEncodedPassword {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}
}
