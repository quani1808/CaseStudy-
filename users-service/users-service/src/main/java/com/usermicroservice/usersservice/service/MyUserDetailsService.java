package com.usermicroservice.usersservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usermicroservice.usersservice.models.User1;
import com.usermicroservice.usersservice.repo.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User1 user1 =repository.findByUsername(username);
		if(user1==null)
		{
			return null;
		}
		String name = user1.getUsername();
		String pwd = user1.getPassword();

		return new User(name, pwd, new ArrayList<>());

	}

}
