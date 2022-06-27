package com.company.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//логика получения информации о валютах
@Component
public class CurrencyUtils {
	
	//
	public String getDifference(String currency) throws JsonProcessingException {
		double latest = getLatestExchange(currency);
		System.out.println("Latest: " + currency + " : " + latest);
		return "High";
	}
	
	public Double getLatestExchange(String currency) throws JsonProcessingException {
		//check for USD/CAD
		if(currency.length() != 3)
			return 0.0;
		double res = 0.0;
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://openexchangerates.org/api/latest.json?app_id=f832c667071b448a9bb3c4baf0f6d11d";
		String responce = restTemplate.getForObject(url, String.class);
		//parsing json with Jackson
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responce); //jsonNode  содержит распарщенный json
		res = Double.parseDouble(String.valueOf(jsonNode.get("rates").get("CAD"))); //доступ в листу с валютами, и получение
		
		return res;
	}
}
