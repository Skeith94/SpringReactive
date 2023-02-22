package com.etra.reactiveNotes.reactiveNotes.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.etra.reactiveNotes.reactiveNotes.models.User;

public interface UserRepository extends ReactiveCrudRepository<User ,Integer> {
	
}