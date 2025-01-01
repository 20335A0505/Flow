package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Repo.AuditRepo;
import com.pojo.Audit;

@RestController
public class AuditController {
	
	@Autowired
	AuditRepo auditRepo;

//	http://localhost:9090/audits?flowId=FLOW1001&flowName=ORDERDETAILS&region=MU
		 // Get all Audits 
	    @CrossOrigin
	    @GetMapping("/audits")
	    public ResponseEntity<?> getAudit(@RequestParam String flowId, @RequestParam String flowName, @RequestParam String region) {
			List<Audit> retrivedAuditTransDetails = auditRepo.findByFlowIdAndFlowNameAndRegion(flowId, flowName, region);
			System.out.println("Audits "+retrivedAuditTransDetails);
			if(retrivedAuditTransDetails != null)
				return new ResponseEntity<>(retrivedAuditTransDetails, HttpStatus.OK);
			else
				return new ResponseEntity<>("Audits is failed to retrive.", HttpStatus.BAD_REQUEST);
		}
}
