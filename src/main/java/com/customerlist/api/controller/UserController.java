package com.customerlist.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.customerlist.api.entity.UserEntity;
import com.customerlist.api.exception.ResultCodeException;
import com.customerlist.api.model.UserAuth;
import com.customerlist.api.model.UserModel;
import com.customerlist.api.security.jwt.JwtTokenUtil;
import com.customerlist.api.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<UserAuth> login(@RequestBody UserModel userModel) throws Exception {

		// Either Email or password are not entered
		if (userModel.getUsername() == null || userModel.getUsername().isEmpty() || userModel.getPassword() == null
				|| userModel.getPassword().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing necessary input");
		}
		UserEntity user;
		try {
			user = userService.isAccountSigninValid(userModel.getPassword(), userModel.getUsername());
		} catch (ResultCodeException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authentication failed: bad userame/password combination");
		}
		UserAuth userAuth =  new UserAuth();
		userAuth.setUsername(user.getUsername());
		userAuth.setPassword(user.getPassword());
		userAuth.setId(user.getId());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		userAuth.setToken(jwtTokenUtil.generateToken(new User(user.getUsername(), user.getPassword(), authorities)));

		
		return  new ResponseEntity<UserAuth>(userAuth, HttpStatus.OK);

	}
	

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<UserAuth> register(@RequestBody UserModel userModel) throws Exception {
		if (userModel.getUsername() == null || userModel.getUsername().isEmpty() || userModel.getPassword() == null
				|| userModel.getPassword().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing necessary input");
		}
	
		UserEntity user;
		try {
			user = userService.registerUser(userModel.getPassword(), userModel.getUsername());
		} catch (ResultCodeException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authentication failed: bad userame/password combination");
		}
		UserAuth userAuth =  new UserAuth();
		userAuth.setUsername(user.getUsername());
		userAuth.setPassword(user.getPassword());
		userAuth.setId(user.getId());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		userAuth.setToken(jwtTokenUtil.generateToken(new User(user.getUsername(), user.getPassword(), authorities)));

		
		return  new ResponseEntity<UserAuth>(userAuth, HttpStatus.OK);

	}

}
