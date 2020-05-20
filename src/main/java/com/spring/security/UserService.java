package com.spring.security;

import java.util.List;

public interface UserService {
	
	public List<String> searchVideos(String searchQuery);
	
	public void onUserInteraction();
	
	public List<String> getRecommendedQuestions(String questions);

}
