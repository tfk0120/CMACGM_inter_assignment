package com.customerlist.api.service;

import com.customerlist.api.entity.UserEntity;
import com.customerlist.api.exception.ResultCodeException;
import com.customerlist.api.model.UserModel;

public interface UserService {
	
	public UserEntity getUserById(int id) throws ResultCodeException;
	
	public UserEntity registerUser(String password, String username) throws ResultCodeException;
	
	public UserEntity getUserByUsername(String username) throws ResultCodeException;

	public UserEntity isAccountSigninValid(String password, String username) throws ResultCodeException ;

}	
