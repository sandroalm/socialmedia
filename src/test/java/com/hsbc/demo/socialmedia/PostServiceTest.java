package com.hsbc.demo.socialmedia;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hsbc.demo.socialmedia.model.Post;
import com.hsbc.demo.socialmedia.model.User;
import com.hsbc.demo.socialmedia.repository.UserRepository;
import com.hsbc.demo.socialmedia.service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostServiceTest {

	
	@Autowired
	private PostService postService;

	@Mock
	private UserRepository userRepository;
	
	private User existentUser;
	
	@Before
	public void setUp() {
		String userId = "user123";
		existentUser = new User(userId);
	}
	

}
