package com.hsbc.demo.socialmedia;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hsbc.demo.socialmedia.model.Post;
import com.hsbc.demo.socialmedia.model.User;
import com.hsbc.demo.socialmedia.repository.PostRepository;
import com.hsbc.demo.socialmedia.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void setUp() {
		String userId = "user123";
		user = userRepository.save(new User(userId));
	}

	@Test
	public void aUserShouldPostA140CharMsg() {
		String text = "Hello World";
		Post post = Post.from(user, text);

		Post result = postRepository.save(post);

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getText(), is(equalTo(text)));
	}

	@Test
	public void shouldFindPostsByUserID() {
		postRepository.save(Post.from(user, "Hello World"));

		List<Post> result = postRepository.findByUserUserNameOrderByDateDesc(user.getUserName());

		assertThat(result, notNullValue());
		assertThat("Should found one post", result.size(), is(equalTo(1)));
	}

	// A user should be able to see a list of the messages posted by all the people
	// they follow, in reverse chronological order.
	@Test
	public void shouldSeeTimeLine() {
		User famousPerson = new User("FamousPerson");
		User dad = new User("dad");
		user.follow(famousPerson);
		user.follow(dad);

		postRepository.save(Post.from(famousPerson, "Hello World"));
		postRepository.save(Post.from(dad, "Hey son"));

		List<Post> result = postRepository.findByUserInOrderByDateDesc(user.getFollowing());
		assertThat("Should contains two posts from the followed people", result.size(), is(equalTo(2)));
	}
}
