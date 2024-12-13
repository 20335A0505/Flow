package com.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Audit;

@Repository
public interface AuditRepo extends MongoRepository<Audit, String> {
	
}
