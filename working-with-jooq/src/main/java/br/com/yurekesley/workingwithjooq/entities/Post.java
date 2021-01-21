package br.com.yurekesley.workingwithjooq.entities;

import static br.com.yurekesley.jooq.tables.Posts.POSTS;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Record;

public class Post {

	public Post(Integer id, String title, String content, LocalDateTime createdOn, List<Comment> comments) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdOn = createdOn;
		this.comments = comments;
	}

	public Post(Integer id, String title, String content, LocalDateTime createdOn) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdOn = createdOn;
	}

	private Integer id;
	private String title;
	private String content;
	private LocalDateTime createdOn;
	private List<Comment> comments = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public static Post from(Record r) {
		Integer id = r.getValue(POSTS.ID, Integer.class);
		String title = r.getValue(POSTS.TITLE, String.class);
		String content = r.getValue(POSTS.CONTENT, String.class);
		LocalDateTime createdOn = r.getValue(POSTS.CREATED_ON, LocalDateTime.class);
		return new Post(id, title, content, createdOn);
	}

}
