package com.CareersApp.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.CareersApp.model.JobPost;

@Component
public interface SearchRepository {

	List<JobPost> findByText(String text);

}
