package com.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Exception;

@Repository
public interface ExceptionRepo extends MongoRepository<Exception, String> {
	
	Exception findByFlowIdAndFlowNameAndRegion(String flowId, String flowName, String region);
	
	Exception findByFlowIdAndExceptionRoute(String flowId, String exceptionRoute);
	
	String deleteByFlowIdAndExceptionRoute(String flowId, String exceptionRoute);
} 
