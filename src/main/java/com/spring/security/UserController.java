package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getVideos")
	public String getVideos(@RequestParam String searchQuery) {
		userService.searchVideos(searchQuery);
		userService.onUserInteraction();
		return "success";
	}
	
	@GetMapping("/getPdf")
	public String getPdf() {
		return "/Users/Abhishek.Wadhawan/Desktop/question.pdf";
	}
	
}
