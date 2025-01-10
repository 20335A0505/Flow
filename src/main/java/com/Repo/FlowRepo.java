package com.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.Flow;

@Repository
public interface FlowRepo extends MongoRepository<Flow, String> {
	
	Flow findTopByOrderByFlowIdDesc();
	
	Flow findByFlowIdAndFlowNameAndRegion(String FlowId, String flowName, String region);

	Flow findByFlowId(String flowId);
	
	@Query("{ 'flowId': :#{#flowId}, 'flowName': :#{#flowName}, 'region': :#{#region} }")
	Flow getVewModel(String flowId,String flowName, String region);

	@Query("{ 'flowId': :#{#flowId}}")
	Flow getByFlowId(String flowId);

}
