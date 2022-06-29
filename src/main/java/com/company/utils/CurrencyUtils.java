package com.company.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

//логика получения информации о валютах
@Component
public class CurrencyUtils {
	
	@Value("${currencyUrl}")
	private String currencyUrl;
	@Value("${oldCurrencyUrlp1}")
	private String oldCurrencyUrlp1;
	@Value("${oldCurrencyUrlp2}")
	private String oldCurrencyUrlp2;
	private ObjectMapper objectMapper;
	private RestTemplate restTemplate;
	
	@Autowired
	public CurrencyUtils(ObjectMapper objectMapper, RestTemplate restTemplate){
		this.objectMapper = objectMapper;
		this.restTemplate = restTemplate;
	}
	
	public double getDifference(String currency) throws JsonProcessingException {
		double latest = getLatestExchange(currency);
		double oldValues = getOldExchange(currency);
		
		System.out.println("Latest: " + currency + " : " + latest + " : " + oldValues);//del
		return (latest - oldValues);
	}
	
	public double getLatestExchange(String currency) throws JsonProcessingException {
		//check for USD/CAD
		if(currency.length() != 3)
			return 0.0;
		double res = 0.0;
		
		String responce = restTemplate.getForObject(currencyUrl, String.class);
		//parsing json with Jackson
		JsonNode jsonNode = objectMapper.readTree(responce); //jsonNode  содержит распарщенный json
		res = Double.parseDouble(String.valueOf(jsonNode.get("rates").get(currency))); //доступ в листу с валютами, и получение
		return res;
	}
	
	public double getOldExchange(String currency) throws JsonProcessingException {
		//check for USD/CAD
		if(currency.length() != 3)
			return 0.0;
		double res = 0.0;
		
		String responce = restTemplate.getForObject(getYesterdayUrl(), String.class);
		//parsing json with Jackson
		JsonNode jsonNode = objectMapper.readTree(responce); //jsonNode  содержит распарщенный json
		res = Double.parseDouble(String.valueOf(jsonNode.get("rates").get(currency))); //доступ в листу с валютами, и получение
		return res;
	}
	
	public String getYesterdayUrl(){
		return oldCurrencyUrlp1 + getYesterday() + oldCurrencyUrlp2;
	}
	public String getYesterday(){
		long yesterdayMilli = System.currentTimeMillis() - 86400000;
		Date yesterday = new Date(yesterdayMilli);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String res = simpleDateFormat.format(yesterday);
		return res;
	}
}
