package com.etra.reactiveNotes.reactiveNotes.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.etra.reactiveNotes.reactiveNotes.models.Note;
import com.etra.reactiveNotes.reactiveNotes.services.NoteServices;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("/notes")
public class NoteController {
		
		private final NoteServices noteServices;
	
		public NoteController(NoteServices noteServices) {
			this.noteServices = noteServices;
		}

		//Endpoint for inserting Notes through a POST request
		@PostMapping("/insert")
		@ResponseStatus(HttpStatus.CREATED)
		public Mono<Note> insertNote(@RequestParam @NotEmpty String text){
			Note note = new Note();
			note.setNote(text);
			Mono<Note> result = noteServices.createNote(note);
			return result.doOnSuccess(item -> new ResponseEntity<>(item, HttpStatus.OK))
					.doOnError(error -> new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR));
		}
		
		//Endpoint for retrieving all Notes from the Database through a GET request
		@GetMapping("/getAll")
		public Flux<Note> getAllNotes(){
			return noteServices.getAllNotes();
		}
		
		//Endpoint for retrieving a Note through a GET request with an id path variable
		@GetMapping("/getFromId/{id}")
		public Mono<Note> getNoteById(@PathVariable("id") @Min(1)  Integer id){
			Mono<Note> note = noteServices.getNoteById(id);
			return note.doOnSuccess(item -> new ResponseEntity<>(item, HttpStatus.OK))
					.doOnError(error -> new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR));
			
		}
	
}
