package com.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReplayRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:Replay")
		.removeHeader("payload")
	    .removeHeader("errorMessage")
		.setHeader("outboundAdapter", simple("${header.outboundQueue.split('\\.')[0]}")) 
		.log("MQ  camel ${body} ---> ${headers}")
	        .to("activemq:queue: ${headers.exceptionRoute.toUpperCase()}?replyTo=direct:replayStatus")
	     ;
		
		from("direct:replayStatus")
        .setBody().constant("Replay Queue Processed");
	}

}

