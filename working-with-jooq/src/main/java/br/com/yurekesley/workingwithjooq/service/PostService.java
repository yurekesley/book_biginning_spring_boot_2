package br.com.yurekesley.workingwithjooq.service;

import static br.com.yurekesley.jooq.tables.Comments.COMMENTS;
import static br.com.yurekesley.jooq.tables.Posts.POSTS;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yurekesley.jooq.tables.records.CommentsRecord;
import br.com.yurekesley.jooq.tables.records.PostsRecord;
import br.com.yurekesley.workingwithjooq.entities.Comment;
import br.com.yurekesley.workingwithjooq.entities.Post;

@Service
@Transactional
public class PostService {

	@Autowired
	private DSLContext dsl;

	public Post createPost(Post post) {
		PostsRecord postsRecord = dsl.insertInto(POSTS).set(POSTS.TITLE, post.getTitle())
				.set(POSTS.CONTENT, post.getContent()).set(POSTS.CREATED_ON, post.getCreatedOn()).returning(POSTS.ID)
				.fetchOne();
		post.setId(postsRecord.getId());
		return post;
	}

	public Comment createComment(Comment comment) {
		CommentsRecord commentsRecord = dsl.insertInto(COMMENTS).set(COMMENTS.POST_ID, comment.getPostId())
				.set(COMMENTS.NAME, comment.getName()).set(COMMENTS.EMAIL, comment.getEmail())
				.set(COMMENTS.CONTENT, comment.getContent()).set(COMMENTS.CREATED_ON, comment.getCreatedOn())
				.returning(COMMENTS.ID).fetchOne();
		comment.setId(commentsRecord.getId());
		return comment;
	}

	public List<Post> getAllPosts() {
		List<Post> posts = new ArrayList<>();
		Result<Record> result = dsl.select().from(POSTS).fetch();
		for (Record record : result) {
			posts.add(Post.from(record));
		}
		return posts;
	}

	public Post findById(Integer postId) {
		Record record = dsl.select().from(POSTS).where(POSTS.ID.eq(postId)).fetchOne();
		if (record != null) {
			Post post = Post.from(record);
			Result<Record> commentRecords = dsl.select().from(COMMENTS).where(COMMENTS.POST_ID.eq(postId)).fetch();
			for (Record r : commentRecords) {
				post.addComment(Comment.from(r));
			}
			return post;
		}
		return null;
	}

	public void deleteComment(Integer commentId) {
		dsl.deleteFrom(COMMENTS).where(COMMENTS.ID.equal(commentId)).execute();
	}

}
