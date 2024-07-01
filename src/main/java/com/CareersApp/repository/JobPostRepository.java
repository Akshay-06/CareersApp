package com.CareersApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CareersApp.model.JobPost;

public interface JobPostRepository extends MongoRepository<JobPost, String>{

}
