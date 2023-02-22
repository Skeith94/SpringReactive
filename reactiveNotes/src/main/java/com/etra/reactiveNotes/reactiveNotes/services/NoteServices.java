package com.etra.reactiveNotes.reactiveNotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etra.reactiveNotes.reactiveNotes.models.Note;
import com.etra.reactiveNotes.reactiveNotes.repository.NoteRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class NoteServices {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public Mono<Note> createNote(Note note){
		log.info("Entering the createNote method");
		return noteRepository.save(note);
	}
	
	public Flux<Note> getAllNotes(){
		log.info("Entering the getAllNotes method");
		return noteRepository.findAll();
	}
	
	public Mono<Note> getNoteById(Integer noteId){
		log.info("Entering the noteId method");
		return noteRepository.findById(noteId);
	}

}
