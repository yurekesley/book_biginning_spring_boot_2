package br.com.yurekesley.domain.entities;

import java.sql.Timestamp;

public class Comment {

	public Comment(Integer id, Integer postId, String name, String email, String content, Timestamp createdOn) {
		super();
		this.id = id;
		this.postId = postId;
		this.name = name;
		this.email = email;
		this.content = content;
		this.createdOn = createdOn;
	}

	private Integer id;
	private Integer postId;
	private String name;
	private String email;
	private String content;
	private Timestamp createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

}