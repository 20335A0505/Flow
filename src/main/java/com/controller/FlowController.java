package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Repo.FlowRepo;
import com.pojo.Flow;
import com.service.FlowService;


@RestController
public class FlowController {
	
	@Autowired
	FlowService flowService;

	@Autowired
	FlowRepo flowRepo;
	
//	Store the Flow
	@CrossOrigin
	 @PostMapping("/stream")
	 public ResponseEntity<String> saveFlow(@RequestBody Map<String,Object> flowDetails) {
	        try {
	        	flowService.saveFlow(flowDetails);
	            return ResponseEntity.ok("Flow saved successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error saving flow: " + e.getMessage());
	        }
	    }
	
//	http://localhost:9090/search?flowId=FLOW1001&flowName=ORDERDETAILS&region=MU
	 // Get all Streams (GET)
    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity<Flow> getAllStreams(@RequestParam String flowId, @RequestParam String flowName, @RequestParam String region) {
    	System.err.println("flowId, flowName, region " +flowRepo.findByFlowIdAndFlowNameAndRegion(flowId,flowName,region));
        try {
            Flow streams = flowRepo.findByFlowIdAndFlowNameAndRegion(flowId,flowName,region);
            System.err.println("get "+streams);
            return ResponseEntity.ok(streams);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
//    http://localhost:9090/flowStreams?flowId=FLOW1001
    @CrossOrigin
    @GetMapping("/flowStreams")
    public ResponseEntity<Flow> getAllStreams(@RequestParam String flowId) {
    	System.err.println("flowId" +flowRepo.findByFlowId(flowId));
        try {
            Flow streams = flowRepo.findByFlowId(flowId);
            System.err.println("get "+streams);
            return ResponseEntity.ok(streams);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

//  http://localhost:9090/showAll
    @CrossOrigin
    @GetMapping("/showAll")
    public List<Flow> getViewAllDetails(){
	    System.out.println("all ");
	    System.out.println(flowRepo.findAll());
	    return flowRepo.findAll();
    }
   
}
