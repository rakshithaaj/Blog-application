package com.blogPostApp.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogPostApp.blogserver.config.JWTService;
import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JWTService jwtService;

	private User loggedInUser;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        String token =jwtService.createToken(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
    
    String token = jwtService.createToken(loggedInUser);
    JwtResponse response = new JwtResponse(token,loggedInUser);
    return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Edit user profile
    @PutMapping("/{userId}")
    public ResponseEntity<User> editUserProfile(@PathVariable int userId, @RequestBody User updatedUser) {
        String token = null;
		if(!jwtService.validateToken(token)) {
        	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        	
        }
    	
    	User editedUser = userService.editUserProfile(userId, updatedUser);
        if (editedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }
}
