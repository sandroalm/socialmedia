package com.hsbc.demo.socialmedia.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String text;

	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	@NotNull
	private User user;

	private Date date;

	private Post(User user, String text) {
		this();
		this.user = user;
		this.text = text;
	}

	public Post() {
		this.date = new Date();
	}

	public Integer getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public static Post from(User user, String text) {
		return new Post(user, text);
	}

}
