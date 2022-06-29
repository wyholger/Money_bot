package com.company.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GifUtils {
	
	public String getGifURL(String res) throws JsonProcessingException {
		//mare request
		RestTemplate rt = new RestTemplate();
		String urlRich = "https://api.giphy.com/v1/gifs/search?api_key=74psuvomPMW3uJlKnLEYj6tmPfcYfWFU&q=rich&limit=20";
		String urlPoor = "https://api.giphy.com/v1/gifs/search?api_key=74psuvomPMW3uJlKnLEYj6tmPfcYfWFU&q=poor&limit=20";
		String response = null;
		if(res.equals("rich"))
			response = rt.getForObject(urlRich, String.class);
		else
			response = rt.getForObject(urlPoor, String.class);
		
		//pars
		ObjectMapper objectMapper = new ObjectMapper();//TODO create bean of ObjectMapper and autoWired
		//create JsonNode tree from response obj by parsing it
		JsonNode jsonNode = objectMapper.readTree(response); //TODO catch exeption here
		//random pic from 0-20
		int random = (int) (Math.random() * 20);
		//pars url and get rid of ""
		String imgURL = jsonNode.get("data").get(random).get("embed_url").toString().replaceAll("\"", ""); //TODO random choose
		
		return imgURL;
	}
}
