package com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Repo.FlowRepo;
import com.pojo.Flow;

import jakarta.annotation.PostConstruct;

@Service
public class FlowService {
	
	@Autowired
	FlowRepo flowRepo;
	
	static int id;
	static String flowId;
	
	@PostConstruct
	public void init() 
	{
		Flow lastFlowID = flowRepo.findTopByOrderByFlowIdDesc();
		System.err.println("Flow "+lastFlowID);
			if(lastFlowID != null) {
				id = Integer.parseInt(lastFlowID.getFlowId().substring(4)) ;
				System.err.println("DBID "+id);
			}
			else
				id = 1000;
	}
	
	public Flow saveFlow(Map<String, Object> flowDetails) {
	    Flow flow = new Flow();
	    id = id + 1;
	    flowId = "FLOW" + id;
	    System.out.println("VALUE " + flowDetails);
	    
	    Map<String, Object> inbound =(Map<String, Object>) flowDetails.get("Inbound");
	    String queue = "";
	    if (inbound != null && inbound.containsKey("queue")) {
	    	queue = (String) inbound.get("queue");
	    } else if (inbound != null && inbound.containsKey("topic")) {
	    	queue = (String) inbound.get("topic");
	    }else if (inbound != null && inbound.containsKey("url")) {
	    	String url = (String) inbound.get("url");
	    	queue = url.substring(url.lastIndexOf('/') + 1);
	    }else if (inbound != null && inbound.containsKey("queuename")) {
	    	queue = (String) inbound.get("queuename");
	    }
	    	

	    System.out.println("flow " + queue + " "); 
	    flow.setFlowId(flowId);
	    flow.setInbound(flowDetails.get("Inbound"));
	    flow.setOutbound(flowDetails.get("Outbound"));
	    flow.setFlowName( queue.split("\\.")[1].toUpperCase());
	    flow.setRegion(queue.split("\\.")[2].toUpperCase());
	    flow.setStages(((String) flowDetails.get("stages")).trim().toUpperCase());
	    flow.setEdges(flowDetails.get("flowedges"));
	    flow.setNodes(flowDetails.get("flownodes"));

	    System.out.println(" DB flow " + flow);

	    flowRepo.save(flow);

	    return flow;
	}

}
