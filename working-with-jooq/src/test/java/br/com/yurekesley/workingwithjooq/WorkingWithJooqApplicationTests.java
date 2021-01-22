package br.com.yurekesley.workingwithjooq;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.yurekesley.workingwithjooq.entities.Comment;
import br.com.yurekesley.workingwithjooq.entities.Post;
import br.com.yurekesley.workingwithjooq.service.PostService;

@SpringBootTest
class WorkingWithJooqApplicationTests {

	@Autowired
	private PostService postService;

	@Test
	public void findAllPosts() {
		List<Post> posts = postService.getAllPosts();
		assertNotNull(posts);
		assertTrue(!posts.isEmpty());
	}

	@Test
	public void findPostById() {
		Post post = postService.findById(1);
		assertNotNull(post);
		System.out.println(post);
		List<Comment> comments = post.getComments();
		System.out.println(comments);
	}

	@Test
	public void createPost() {
		Post post = new Post(0, "My new Post", "This is my new test post", LocalDateTime.now());
		Post savedPost = postService.createPost(post);
		Integer id = savedPost.getId();
		Post newPost = postService.findById(id);
		assertEquals("My new Post", newPost.getTitle());
		assertEquals("This is my new test post", newPost.getContent());
	}

	@Test
	public void createComment() {
		Integer postId = 1;
		Comment comment = new Comment(0, postId, "User4", "user4@gmail.com", "This is my new comment on post1",
				LocalDateTime.now());
		Comment savedComment = postService.createComment(comment);
		Post post = postService.findById(postId);
		List<Comment> comments = post.getComments();
		assertNotNull(comments);
		for (Comment comm : comments) {
			if (savedComment.getId() == comm.getId()) {
				assertEquals("User4", comm.getName());
				assertEquals("user4@gmail.com", comm.getEmail());
				assertEquals("This is my new comment on post1", comm.getContent());
			}
		}
	}

}
