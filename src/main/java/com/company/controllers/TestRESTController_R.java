package com.company.controllers;


import com.company.utils.CurrencyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class TestRESTController_R {
	
	 private CurrencyUtils currencyUtils;
	@Autowired
	public TestRESTController_R(CurrencyUtils currencyUtils){
		this.currencyUtils = currencyUtils;
	}
	
	@ResponseBody
	@GetMapping("/sayHey")
	public String sayHo(){
		return "Hello world";
	}
	
	@GetMapping("/gif")
	public String gifDemo(){
		System.out.println(currencyUtils.getDifference());
		return "GifResponce";
	}
	
}
