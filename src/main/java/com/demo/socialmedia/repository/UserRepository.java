package com.demo.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.socialmedia.model.User;

@Repository
public interface UserRepository extends JpaRepository<User	,String>{

	User findByUserName(String userName);


}
