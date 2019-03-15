package com.demo.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.socialmedia.model.Post;
import com.demo.socialmedia.model.User;
import com.demo.socialmedia.repository.PostRepository;
import com.demo.socialmedia.service.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocialMediaServiceTest {

	@Autowired
	PostService socialMediaService;

	@Mock
	PostRepository postRepository;

	// Wall
	@Test
	public void aUserShouldSeeListOfMessagesTheyHavePostedReverseChronologicalOrder() {
		String userId = "user123";
		List<Post> list = new ArrayList<>();
		list.add(Post.from(new User(userId), "Hello World"));

		Mockito.when(postRepository.findByUserUserNameOrderByDateDesc(userId)).thenReturn(list);

		List<Post> result = socialMediaService.findPostsByUserId(userId);
		Assert.assertNotNull(result);

	}

	//
	// Following
	// A user should be able to follow another user. Following doesn't have to be
	// reciprocal: Alice can follow Bob without Bob having to follow Alice.
	//
	// Timeline
	// A user should be able to see a list of the messages posted by all the people
	// they follow, in reverse chronological order.
}
