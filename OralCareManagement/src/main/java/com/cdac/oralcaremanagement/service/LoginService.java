package com.cdac.oralcaremanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdac.oralcaremanagement.dao.UserRepository;
import com.cdac.oralcaremanagement.entity.User;

@Service
public class LoginService {

	@Autowired
	private UserRepository patientRepo;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public boolean authenticate(User user) {
		User dbUser = patientRepo.findByEmail(user.getEmail());
		if (dbUser != null && bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword()) ) {
			return true; // Authentication successful
		}
		return false; // Authentication failed
	}
}