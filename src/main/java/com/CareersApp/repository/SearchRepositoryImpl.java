package com.CareersApp.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.CareersApp.model.JobPost;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@Component
public class SearchRepositoryImpl implements SearchRepository{
	
	@Autowired
	MongoClient mongoClient;
	@Autowired
	MongoConverter converter;

	@Override
	public List<JobPost> findByText(String text) {
		// TODO Auto-generated method stub
		List<JobPost> posts = new ArrayList<>();
		
		
		// You can get this code from MongoDB Atlas, create a pipeline and convert it to code
		MongoDatabase database = mongoClient.getDatabase("CareersApp");
		MongoCollection<Document> collection = database.getCollection("JobPost");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("index", "default")
		            .append("text", 
		    new Document("query", text)
		                .append("path", Arrays.asList("profile", "desc", "techs")))), 
		    new Document("$sort", 
		    new Document("exp", 1L)), 
		    new Document("$limit", 5L)));
		
		
		result.forEach(doc->posts.add(converter.read(JobPost.class, doc)));
		
		return posts;
	}

}
