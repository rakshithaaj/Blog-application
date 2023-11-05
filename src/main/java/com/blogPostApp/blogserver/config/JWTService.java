package com.blogPostApp.blogserver.config;

import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.User;

@Service
public interface JWTService {
	
	String createToken(User user);
    User parseToken(String token);
    boolean validateToken(String token);
	

}
