package br.com.yurekesley.workingwithjooq.entities;

import static br.com.yurekesley.jooq.tables.Comments.COMMENTS;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.jooq.Record;

public class Comment {

	private Integer id;
	private Integer postId;
	private String name;
	private String email;
	private String content;
	private LocalDateTime createdOn;

	public Comment(Integer id, Integer postId, String name, String email, String content, LocalDateTime createdOn) {
		this.id = id;
		this.postId = postId;
		this.name = name;
		this.email = email;
		this.content = content;
		this.createdOn = createdOn;
	}

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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return this.content;
	}

	public static Comment from(Record r) {
		Integer id = r.getValue(COMMENTS.ID, Integer.class);
		Integer postId = r.getValue(COMMENTS.POST_ID, Integer.class);
		String name = r.getValue(COMMENTS.NAME, String.class);
		String email = r.getValue(COMMENTS.EMAIL, String.class);
		String content = r.getValue(COMMENTS.CONTENT, String.class);
		LocalDateTime createdOn = r.getValue(COMMENTS.CREATED_ON, LocalDateTime.class);
		return new Comment(id, postId, name, email, content, createdOn);
	}

}
