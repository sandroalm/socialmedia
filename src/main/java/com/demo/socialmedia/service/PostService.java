package com.demo.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.socialmedia.model.Post;
import com.demo.socialmedia.model.User;
import com.demo.socialmedia.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

	public Post save(Post post) {
		User user = userService.findByUserName(post.getUser().getUserName());
		if (user != null) {
			post.setUser(user);
		}
		return postRepository.save(post);
	}

	public List<Post> findPostsByUserId(String userId) {
		return postRepository.findByUserUserNameOrderByDateDesc(userId);
	}

	public List<Post> findByUserInOrderByDateDesc(User user) {
		return postRepository.findByUserInOrderByDateDesc(user.getFollowing());
	}

}
