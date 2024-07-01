package com.CareersApp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CareersApp.model.JobPost;
import com.CareersApp.repository.JobPostRepository;
import com.CareersApp.repository.SearchRepository;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class JobPostController {
	
	@Autowired
	JobPostRepository repo;
	@Autowired
	SearchRepository srepo;

	@Hidden
	@RequestMapping("/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}

	

	@GetMapping("posts")
	public List<JobPost> getAllPosts(){
		return repo.findAll();
	}
	
	@GetMapping("posts/{text}")
	public List<JobPost> searchPosts(@PathVariable String text){
		return srepo.findByText(text);
	}
	
	@PostMapping("post")
	public JobPost addPost(@RequestBody JobPost post) {
		return repo.save(post);
	}
	
}
