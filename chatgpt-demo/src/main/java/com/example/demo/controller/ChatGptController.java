package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.Request;
import com.example.demo.dto.Response;

@RestController
public class ChatGptController {
		
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.api.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/chatgpt")
	public String chatgptResponse(@RequestParam("prompt") String prompt) {
		
		Request request = new Request(model,prompt);
		Response chatGptResponse = restTemplate.postForObject(apiUrl,request,Response.class);
		
		return chatGptResponse.getChoices().get(0).getMessage().getContent();
	}
}
