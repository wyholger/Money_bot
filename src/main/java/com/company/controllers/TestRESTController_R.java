package com.company.controllers;


import com.company.utils.CurrencyUtils;
import com.company.utils.GifUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/romzes")
public class TestRESTController_R {
	
	 private CurrencyUtils currencyUtils;
	 private GifUtils gifUtils;
	@Autowired
	public TestRESTController_R(CurrencyUtils currencyUtils,
	                            GifUtils gifUtils){
		this.currencyUtils = currencyUtils;
		this.gifUtils = gifUtils;
	}
	
	@ResponseBody ///delete
	@GetMapping("/sayHey")
	public String sayHo(){
		return "Hello world";
	}
	
	@GetMapping("/gif")
	public String gifDemo(){
		return "GifResponce";
	}
	
	@GetMapping("/get_cur")
	public String getCurrency(@RequestParam(name="currency", required = false)
	                         String currency, Model model) throws JsonProcessingException {
		
		if(currency != null && currency != "")
		{
			double res = currencyUtils.getDifference(currency);
			String imgURL = gifUtils.getGifURL(res);
			
			model.addAttribute("searchResult", res);
			model.addAttribute("imgurl", imgURL);
			model.addAttribute("kostyl", "kostyl");//костыль, иначе картинка  выводится до получения значения
			return "ChooseMoney";
		}
		return "ChooseMoney";
	}
	
}
