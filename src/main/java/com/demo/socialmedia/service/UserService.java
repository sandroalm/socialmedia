package com.demo.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.socialmedia.model.User;
import com.demo.socialmedia.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(User user) {
		return repository.save(user);
	}

	public User findByUserName(String userName) {
		return repository.findByUserName(userName);
	}

}
