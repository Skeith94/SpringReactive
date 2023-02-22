package com.etra.reactiveNotes.reactiveNotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etra.reactiveNotes.reactiveNotes.models.User;
import com.etra.reactiveNotes.reactiveNotes.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	public Mono<User> createUser(User user){
		log.info("Entering the createUser method");
		return userRepository.save(user);
	}
	
	public Mono<User> getUserById(Integer userId){
		log.info("Entering the userId method");
		return userRepository.findById(userId);
	}

}
