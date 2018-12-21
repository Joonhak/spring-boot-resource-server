package io.joonhak.web;

import io.joonhak.entity.Post;
import io.joonhak.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PreAuthorize("#oauth2.hasScope('read')")
	@GetMapping("/ALL")
	public List<Post> getPostList(@AuthenticationPrincipal OAuth2Authentication authentication, HttpServletRequest req) {
		log.info("AUTHENTICATION INFORMATION : {}", authentication);
		log.info("IS ADMIN? : {}", req.isUserInRole("ADMIN"));
		log.info("OAuth2Authentication - authorities : {}", authentication.getAuthorities());
		log.info("UserAuthentication - authorities : {}", authentication.getUserAuthentication().getAuthorities());
		log.info("SCOPES : {}", authentication.getOAuth2Request().getScope());
		return postService.getAllPosts();
	}
	
	@GetMapping("/{id}")
	public Post getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}
	
}
