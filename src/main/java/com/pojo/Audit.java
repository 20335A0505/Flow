package com.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection ="Audit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audit {

	
	@Id
	String  	messageId;	
	
	
	String		flowId, 				
				flowName,
				region,
				inboundQueue, 
				outboundQueue,
				sourceTimeStamp,
				targetTimeStamp,
				stages,
				inAuditStatus,     		
				outAuditStatus,	
				payload
			;

	}
