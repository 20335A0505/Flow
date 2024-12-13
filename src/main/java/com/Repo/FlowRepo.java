package com.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.Flow;

@Repository
public interface FlowRepo extends MongoRepository<Flow, String> {
	
	Flow findTopByOrderByFlowIdDesc();
	
	Flow findByFlowIdAndFlowNameAndRegion(String FlowId, String flowName, String region);

	Flow findByFlowId(String flowId);
}
