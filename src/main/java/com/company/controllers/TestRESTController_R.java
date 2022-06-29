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
import java.net.MalformedURLException;



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
	
	@ResponseBody //return information into browser, without html
	@GetMapping("/showGif")
	public String showGif(@RequestParam(name = "val", required = false) String picVal) throws JsonProcessingException {
		System.out.println(picVal);//!del
		
		String imgURL = gifUtils.getGifURL(picVal);
		
		return "<iframe src=\"" + imgURL + "\" width=\"480\" height=\"295\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen>";
		
	}
	
	@GetMapping("/get_cur")
	public String getCurrency(@RequestParam(name="currency", required = false)
	                         String currency, Model model) throws JsonProcessingException, MalformedURLException {
		
		if(currency != null && currency != "")
		{
			double res = currencyUtils.getDifference(currency);
			String s;
			if (res > 0)
				s = "rich";
			else
				s = "poor";
			return "redirect:showGif?val=" + s;
		}
		return "ChooseMoney";
	}
	@GetMapping("/gif")
	public String gifDemo(){
		return "GifResponce";
	}
}
