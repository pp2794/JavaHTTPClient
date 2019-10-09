package com.piy.javaHTTPClient.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PojoToJson {
	
	public String convertPojoToJson(Object ob) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		String json = mapper.writeValueAsString(ob);
		
		return json;
		
	}

}
