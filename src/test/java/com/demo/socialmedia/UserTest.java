package com.demo.socialmedia;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.socialmedia.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Test
	public void shouldFollowAnotherUser() {
		String userId = "user123";
		User user = new User(userId);

		User famous = new User("veryFamouseOne");
		user.follow(famous);
		assertTrue(user.isFoolowing(famous));
	}
}
