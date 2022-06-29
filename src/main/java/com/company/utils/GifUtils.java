package com.company.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;

@Component
public class GifUtils {
	@Value("${poor}")
	private String urlPoor;
	@Value("${rich}")
	private String urlRich;
	
	private ObjectMapper objectMapper;
	private RestTemplate restTemplate;
	
	@Autowired
	public GifUtils(ObjectMapper objectMapper, RestTemplate restTemplate){
		this.objectMapper = objectMapper;
		this.restTemplate = restTemplate;
	}
	public String getGifURL(String res) throws JsonProcessingException {
		
		String response = null;
		if(res.equals("rich"))
			response = restTemplate.getForObject(urlRich, String.class);
		else
			response = restTemplate.getForObject(urlPoor, String.class);
		
		//pars
		//create JsonNode tree from response obj by parsing it
		JsonNode jsonNode = objectMapper.readTree(response); //TODO catch exception here
		//random pic from 0-20
		int random = (int) (Math.random() * 20);
		//pars url and get rid of ""
		String imgURL = jsonNode.get("data").get(random).get("embed_url").toString().replaceAll("\"", ""); //TODO random choose
		
		return imgURL;
	}
}
