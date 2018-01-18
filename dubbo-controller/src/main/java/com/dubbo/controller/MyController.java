package com.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dubbo.service.TestService;

@Controller
public class MyController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/test")
	@ResponseBody
	public String testSay(@RequestParam(value = "name",defaultValue = "") String name){
		StringBuffer sb = new StringBuffer();
		sb.append("Dubbo: ").append(testService.sayHello(name));
		return sb.toString();
	}
	
}
