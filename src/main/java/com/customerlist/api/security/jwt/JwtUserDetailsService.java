package com.customerlist.api.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.customerlist.api.entity.UserEntity;
import com.customerlist.api.exception.ResultCodeException;
import com.customerlist.api.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	public static final String ROLE_USER = "USER";

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity account;
		try {
			account = userService.getUserByUsername(username);
		} catch (ResultCodeException ex) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(ROLE_USER));
		return new User(account.getUsername(), account.getPassword(), authorities);
	}

}
