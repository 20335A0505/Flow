package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Repo.ExceptionRepo;
import com.pojo.Exception;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ReplayContoller {

	@Autowired
	ExceptionRepo exceptionrepo;
	
	@Autowired
	ProducerTemplate producerTemplate;
	
	@CrossOrigin
	@GetMapping("/getAllReplays")
	public ResponseEntity<?> getAllReplays() {
		List<Exception> Allreplays = exceptionrepo.findAll();
//		System.err.println("exceptionDetails "+Allreplays);
		List<Exception> replayable = new ArrayList<Exception>() ;
		for(Exception replay : Allreplays) {
			System.err.println("replay.getReplayableStatus() "+replay.getReplayableStatus());
			if(replay.getReplayableStatus().equalsIgnoreCase("true") ) {
				replayable.add(replay);
			}
		}
		if(Allreplays != null)
			return new ResponseEntity<>(replayable, HttpStatus.OK);
		else
			return new ResponseEntity<>("Data is not Retrived from Exception for replay.", HttpStatus.BAD_REQUEST);
	}
	
//	Handling Replay
	@CrossOrigin
	@GetMapping("/getReplay")
	public String getReplayById(@RequestParam String flowId, @RequestParam String exceptionRoute) {
		Exception replay = exceptionrepo.findByFlowIdAndExceptionRoute(flowId,exceptionRoute);
//		System.err.println("exceptionDetails replay "+replay);
		  ObjectMapper objectMapper = new ObjectMapper();
		  Map<String, Object> header = objectMapper.convertValue(replay, Map.class);
//		  System.err.println("exceptionMap replay "+header);
	    producerTemplate.sendBodyAndHeaders("direct:Replay",header.get("payload") ,header);
        String replayResponse = (String) producerTemplate.requestBodyAndHeader("direct:replayStatus", null, "CamelHttpResponse", String.class);
	    System.err.println("replayResponse  "+replayResponse);
	   if(replayResponse != null)
		   return exceptionrepo.deleteByFlowIdAndExceptionRoute(flowId,exceptionRoute);
		else
			return "Replay unsucccessful";
	}
}
