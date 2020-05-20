package com.spring.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3438385000868929821L;
	private String userName;
	private ScheduledFuture<?> pdfScheduler;
	private String lastQuestion;
	
	public MyUserDetails(String userName) {
		this.userName = userName;
	}
	
	public String getLastQuestion() {
		return lastQuestion;
	}

	public void setLastQuestion(String question) {
		this.lastQuestion = question;
	}

	public MyUserDetails() {
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return "pass";
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public ScheduledFuture<?> getPdfScheduler() {
		return pdfScheduler;
	}

	public void setPdfScheduler(ScheduledFuture<?> scheduler) {
		this.pdfScheduler = scheduler;
	}

}
