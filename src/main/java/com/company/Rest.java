package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Rest
{
	public static void main(String[] args)
	{
		SpringApplication.run(Rest.class);
	}
	
	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
}
