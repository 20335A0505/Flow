package com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Repo.ExceptionRepo;
import com.pojo.Exception;
@RestController
public class ExceptionController {

	@Autowired
	ExceptionRepo exceptionRepo;
	
//	http://localhost:9090/exception?flowId=FLOW1001&flowName=ORDERDETAILS&region=MU
	@CrossOrigin
	@GetMapping("/exception")
	public ResponseEntity<?> getException(@RequestParam String flowId, @RequestParam String flowName, @RequestParam String region) {
		System.err.println(flowId+" "+region+" "+flowName);
		Exception exception = exceptionRepo.findByFlowIdAndFlowNameAndRegion(flowId,flowName,region);
		if(exception != null)
			return new ResponseEntity<>(exception, HttpStatus.OK);
		else
			return new ResponseEntity<>("Failed to retrive the exception", HttpStatus.BAD_REQUEST);
	}
	
}
