package com.company.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GifUtils {
	
	public String getGifURL(double res) throws JsonProcessingException {
		//mare request
		RestTemplate rt = new RestTemplate();
		String url = "https://api.giphy.com/v1/gifs/search?api_key=74psuvomPMW3uJlKnLEYj6tmPfcYfWFU&q=rich&limit=3";
		
		String response = rt.getForObject(url, String.class);
		System.out.println(response);
		//pars
		ObjectMapper objectMapper = new ObjectMapper();//TODO create bean of ObjectMapper and autoWired
		//create JsonNode tree from response obj by parsing it
		JsonNode jsonNode = objectMapper.readTree(response); //TODO catch exeption here
		String imgURL = jsonNode.get("data").get(0).get("embed_url").toString(); //TODO random choose
		
		System.out.println(imgURL);//
		
		return imgURL;
	}
}
