package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyRestController
{
	public static class RestResponse
	{
		private String param1;
		private String param2;
		private Double value;

		public String getParam1() {
			return param1;
		}

		public void setParam1(String param1) {
			this.param1 = param1;
		}

		public String getParam2() {
			return param2;
		}

		public void setParam2(String param2) {
			this.param2 = param2;
		}

		@Override
		public String toString() {
			return "RestResponse{" +
					"param1='" + param1 + '\'' +
					", param2='" + param2 + '\'' +
					'}';
		}
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestResponse restMethod(String name) throws JsonProcessingException {
		RestResponse result = new RestResponse();
		RestTemplate rt = new RestTemplate();

		String url1 = "https://openexchangerates.org/api/latest.json?app_id=f832c667071b448a9bb3c4baf0f6d11d";

		String response = rt.getForObject(url1, String.class);

		//parsing json with jackson
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response); //jsonNode  содерэит распарщенный json
		System.out.println("CAD: " + jsonNode.get("rates").get("CAD")); //доступ в листу с валютами, и получение конкретной валюты по ключу

//		System.out.println(response);
//		result.setParam1("Hello");
//		result.setParam2(name);
//		System.out.println(result);
		return result;
	}
}
