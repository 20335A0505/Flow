package com.pojo;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.Nullable;

import lombok.Data;

@Data
@Document(collection = "Flow")
public class Flow {

	private String flowId;
	private String flowName;
	private String stages;
	private String region;
	@Nullable
	@Field("transdata")
	private String transdata;
	private Object inbound;
	private Object outbound;
	private Object nodes;
    private Object edges;
}


