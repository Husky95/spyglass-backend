package com.skillstorm.spyglass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spyglass.dto.ResponseDTO;
import com.skillstorm.spyglass.dto.UserDTO;
import com.skillstorm.spyglass.session.InMemorySessionRegistry;
import com.skillstorm.spyglass.user.UserEntity;
import com.skillstorm.spyglass.user.UserRepository;


@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/user")
public class AuthenticationController {
    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public InMemorySessionRegistry sessionRegistry;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoders;
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
  
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        final String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);
        response.setUsername(user.getUsername());
        response.setId(user.getId());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user){
    	System.out.println("test");
    	String encodePassword = passwordEncoders.encode(user.getPassword());
    	System.out.println(encodePassword);
    	user.setPassword(encodePassword);
    	UserEntity newUser = userRepository.save(user);
    	
        return ResponseEntity.ok(newUser);
	}
    
    
} 