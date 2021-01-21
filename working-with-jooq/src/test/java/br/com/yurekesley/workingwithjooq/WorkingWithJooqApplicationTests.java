package br.com.yurekesley.workingwithjooq;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
