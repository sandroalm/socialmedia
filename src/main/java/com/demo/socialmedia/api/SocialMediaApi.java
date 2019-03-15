package com.demo.socialmedia.api;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.socialmedia.model.Post;
import com.demo.socialmedia.model.User;
import com.demo.socialmedia.service.PostService;
import com.demo.socialmedia.service.UserService;

@RestController
@RequestMapping("/social")
public class SocialMediaApi {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/posts/{userName}", method = RequestMethod.GET)
	public List<Post> getPosts(@PathVariable String userName) {
		return postService.findPostsByUserId(userName);
	}

	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	@Consumes("application/json")
	public Post post(@Valid @RequestBody Post post) {
		return postService.save(post);
	}

	@RequestMapping(value = "/users/follow/{userName}", method = RequestMethod.POST)
	@Consumes("application/json")
	public User follow(@PathVariable String userName, @RequestBody User request) {
		User toFollow = userService.findByUserName(userName);
		User currentUser = userService.findByUserName(request.getUserName());
		currentUser.follow(toFollow);
		return userService.save(currentUser);
	}

	@RequestMapping(value = "/users/{userName}", method = RequestMethod.GET)
	public User findUser(@PathVariable String userName) {
		return userService.findByUserName(userName);
	}

	@RequestMapping(value = "/users/timeline", method = RequestMethod.POST)
	@Consumes("application/json")
	public List<Post> timeline(@RequestBody User user) {
		// refresh	
		user = userService.findByUserName(user.getUserName());
		return postService.findByUserInOrderByDateDesc(user);
	}
}
