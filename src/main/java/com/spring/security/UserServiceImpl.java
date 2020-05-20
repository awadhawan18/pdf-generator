package com.spring.security;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

@Service
public class UserServiceImpl implements UserService {

	//populate search results from Db with list of videos
	@Override
	public List<String> searchVideos(String searchQuery) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof MyUserDetails) {
        	MyUserDetails userDetails = (MyUserDetails) principal;
        	userDetails.setLastQuestion(searchQuery);
        }
		return new ArrayList<>();
	}

	//Schedules a pdf generator for future.
	@Override
	public void onUserInteraction() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof MyUserDetails) {
        	MyUserDetails userDetails = (MyUserDetails) principal;
        	ScheduledFuture<?> futureTask = userDetails.getPdfScheduler();
        	if(futureTask==null) {
        		futureTask = getPdfScheduledTask(userDetails.getLastQuestion(), getRecommendedQuestions(userDetails.getLastQuestion()));
        		userDetails.setPdfScheduler(futureTask);
        	}
        	else {
        		futureTask = userDetails.getPdfScheduler();
        		if(!futureTask.isDone()) {
        			futureTask.cancel(false);
        		} 
    	     	futureTask = getPdfScheduledTask(userDetails.getLastQuestion(), getRecommendedQuestions(userDetails.getLastQuestion()));
    	     	userDetails.setPdfScheduler(futureTask);
        	}
        }
		
	}
	
	private ScheduledFuture<?> getPdfScheduledTask(String searchQuery,List<String> questions){
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		 Runnable task = new Runnable() {
	            public void run() {
	                try {
						new QuestionPdfGenerator().generatePdf(searchQuery, questions);
					} catch (FileNotFoundException | DocumentException e) {
						e.printStackTrace();
					}
	            }
	     };
	     int delay = 5;
	     ScheduledFuture<?> schedule = scheduler.schedule(task, delay, TimeUnit.SECONDS);
	     return schedule;
	}

	//gets recommended questions from service
	@Override
	public List<String> getRecommendedQuestions(String question) {
		return Arrays.asList("Question1: Sample Test Question1", "Question2: Sample Test Question2", "Question3: Sample Test Question3",
				"Question4: Sample Test Question4", "Question5: Sample Test Question5");
	}
	
	
	
}
