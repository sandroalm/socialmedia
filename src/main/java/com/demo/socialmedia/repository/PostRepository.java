package com.demo.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.socialmedia.model.Post;
import com.demo.socialmedia.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUserUserNameOrderByDateDesc(String userId);
	
	List<Post> findByUserInOrderByDateDesc(List<User> following);
}
