package io.joonhak.service;

import io.joonhak.entity.Post;
import io.joonhak.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	public List<Post> getAllPosts() {
		return postRepo.findAll();
	}
	
	public Post getPost(Long id) {
		return postRepo.findById(id)
				.orElseGet( () -> new Post("DEFAULT POST", "SYSTEM", "SOMETHING WENT WRONG.. PLEASE TRY AGAIN!") );
	}
	
	@PostConstruct
	public void init() {
		var post1 = new Post("title1", "author", "content--------------------------");
		var post2 = new Post("title2", "author", "content--------------------------");
		var post3 = new Post("title3", "author", "content--------------------------");
		var post4 = new Post("title4", "author", "content--------------------------");
		var post5 = new Post("title5", "author", "content--------------------------");
		
		log.info( "POSTS : {}", postRepo.saveAll(Arrays.asList(post1, post2, post3, post4, post5)) );
	}
	
}
