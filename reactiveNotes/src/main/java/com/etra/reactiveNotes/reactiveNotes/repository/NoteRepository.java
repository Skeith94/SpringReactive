package com.etra.reactiveNotes.reactiveNotes.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.etra.reactiveNotes.reactiveNotes.models.Note;

public interface NoteRepository extends ReactiveCrudRepository<Note ,Integer> {
	
}