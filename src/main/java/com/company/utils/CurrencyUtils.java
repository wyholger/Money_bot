package com.company.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

//логика получения информации о валютах
@Component
public class CurrencyUtils {
	
	//
	public double getDifference(String currency) throws JsonProcessingException {
		double latest = getLatestExchange(currency);
		double oldValues = getOldExchange(currency);
		
		System.out.println("Latest: " + currency + " : " + latest + " : " + oldValues);
		return (latest - oldValues);
	}
	
	public double getLatestExchange(String currency) throws JsonProcessingException {
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
		res = Double.parseDouble(String.valueOf(jsonNode.get("rates").get(currency))); //доступ в листу с валютами, и получение
		return res;
	}
	
	public double getOldExchange(String currency) throws JsonProcessingException {
		//check for USD/CAD
		if(currency.length() != 3)
			return 0.0;
		double res = 0.0;
		RestTemplate restTemplate = new RestTemplate();
		
		String url = getYesterdayUrl();
		String responce = restTemplate.getForObject(url, String.class);
		//parsing json with Jackson
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responce); //jsonNode  содержит распарщенный json
		res = Double.parseDouble(String.valueOf(jsonNode.get("rates").get(currency))); //доступ в листу с валютами, и получение
		return res;
	}
	
	public String getYesterdayUrl(){
		String s1 = "https://openexchangerates.org/api/historical/";
		String s2 = getYesterday();
		String s3 = ".json?app_id=f832c667071b448a9bb3c4baf0f6d11d";
		String res = s1 + s2 + s3;
		return res;
	}
	public String getYesterday(){
		long yesterdayMilli = System.currentTimeMillis() - 86400000;
		Date yesterday = new Date(yesterdayMilli);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String res = simpleDateFormat.format(yesterday);
		return res;
	}
}
