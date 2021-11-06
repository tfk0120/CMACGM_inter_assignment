package com.customerlist.api.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.customerlist.api.entity.UserEntity;
import com.customerlist.api.exception.ResultCode;
import com.customerlist.api.exception.ResultCodeException;
import com.customerlist.api.model.UserModel;
import com.customerlist.api.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserEntity getUserById(int id) throws ResultCodeException {
		try {
			return userRepository.getById(id);
		} catch (EntityNotFoundException ex) {
			throw ResultCode.RESOURCE_NOT_FOUND.exception();

		}

	}

	@Override
	public UserEntity getUserByUsername(String username) throws ResultCodeException {
		// TODO Auto-generated method stub
		UserEntity user = userRepository.findByUsername(username);
		if (user == null)
			throw ResultCode.RESOURCE_NOT_FOUND.exception();
		return user;
	}

	public UserEntity isAccountSigninValid(String password, String username) throws ResultCodeException {
		UserEntity user = getUserByUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// String hashedPassword = passwordEncoder.encode(password);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		} else {
			throw ResultCode.RESOURCE_NOT_FOUND.exception();
		}

	}

	@Override
	public UserEntity registerUser(String password, String username) throws ResultCodeException {
	
		return userRepository.save(new UserEntity(username, password));
	}


}
